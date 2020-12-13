package xyz.ottersbach.hwrscheduler.icsparser

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.extractors.*

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
            extractRooms(entry),
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