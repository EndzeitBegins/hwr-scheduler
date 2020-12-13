package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.descriptionEntry
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.lecturers

internal fun extractLecturers(entry: LessonEvent): List<String> {
    val lecturersFromDescription = entry.descriptionEntry.lecturers

    return lecturersFromDescription
        // remove backslash before suffixed first name abbreviations
        ?.replace("\\\\,\\s*(\\w)\\.\\s*(\\\\,|$)".toRegex(), ", \$1.\$2")
        // split different lecturers
        ?.split("\\,")
        ?.map(String::trim) ?: emptyList()
}