package xyz.ottersbach.hwrscheduler.icsparser.internal

import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.*

internal data class LessonEvent(val string: String)

internal val LessonEvent.uidEntry
    get() = UidEntry(extractEntry("UID:", "SUMMARY:"))
internal val LessonEvent.locationEntry
    get() = LocationEntry(extractEntry("LOCATION:", "DESCRIPTION:"))
internal val LessonEvent.descriptionEntry
    get() = DescriptionEntry(extractEntry("DESCRIPTION:", "DTSTART"))
internal val LessonEvent.startTimeEntry
    get() = StartTimeEntry(extractEntry("DTSTART", "DTEND"))
internal val LessonEvent.endTimeEntry
    get() = EndTimeEntry(extractEntry("DTEND", "\n"))

private fun LessonEvent.extractEntry(startDelimiter: String, endDelimiter: String): String {
    val start = string.indexOf(startDelimiter)
    val end = string.indexOf(endDelimiter, startIndex = start)

    return string
        .substring(start, end)
        .removeIndentedLinebreaks()
}

private fun String.removeIndentedLinebreaks() = replace("\\s*[\\n\\r]+\\s*".toRegex(), "")
