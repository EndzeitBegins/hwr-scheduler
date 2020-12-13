package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.descriptionEntry
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.note
import xyz.ottersbach.hwrscheduler.icsparser.internal.summaryEntry

internal fun extractNote(entry: LessonEvent): String? {
    val noteFromSummary = entry.summaryEntry.note
    val noteFromDescription = entry.descriptionEntry.note

    return noteFromSummary ?: noteFromDescription
}