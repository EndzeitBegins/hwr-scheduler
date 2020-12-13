package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.descriptionEntry
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.lecturers
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.note
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.parts
import xyz.ottersbach.hwrscheduler.icsparser.internal.summaryEntry

internal fun extractLecturers(entry: LessonEvent): List<String> {
    val lecturersFromSummary = entry.summaryEntry.lecturers
    val lecturersFromDescription = entry.descriptionEntry.lecturers

    val lecturersString = lecturersFromSummary ?: lecturersFromDescription

    return lecturersString
        // remove backslash before suffixed first name abbreviations
        ?.replace("\\\\,\\s*(\\w)\\.\\s*(\\\\,|$)".toRegex(), ", \$1.\$2")
        // split different lecturers
        ?.split("\\,")
        ?.map(String::trim) ?: emptyList()
}