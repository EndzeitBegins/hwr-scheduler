package xyz.ottersbach.hwrscheduler.icsparser

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.extractors.*
import xyz.ottersbach.hwrscheduler.icsparser.internal.extractors.extractEndTime
import xyz.ottersbach.hwrscheduler.icsparser.internal.extractors.extractModule
import xyz.ottersbach.hwrscheduler.icsparser.internal.extractors.extractStartTime
import xyz.ottersbach.hwrscheduler.icsparser.internal.extractors.extractUid

public fun parse(icsFileContent: String): List<Lesson> {
    val relevantContent = retrieveRelevantContent(icsFileContent)
    val entries = splitUpEntries(relevantContent)

    return entries.map {
        val entry = LessonEvent(it)

        Lesson(
            extractUid(entry),
            extractStartTime(entry),
            extractEndTime(entry),
            extractModule(entry),
            extractLecturers(entry),
            extractRoom(entry),
            extractType(entry),
            extractNote(entry)
        )
    }
}

private fun retrieveRelevantContent(icsFileContent: String): String {
    val startOfFirstLessonEntry = icsFileContent.indexOf("BEGIN:VEVENT")
    val endOfLastLessonEntry = icsFileContent.lastIndexOf("END:VEVENT")

    return icsFileContent.substring(startOfFirstLessonEntry, endOfLastLessonEntry)
}

private fun splitUpEntries(relevantContent: String) = relevantContent.split("END:VEVENT")