package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.descriptionEntry
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.note
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.parts
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.type
import xyz.ottersbach.hwrscheduler.icsparser.internal.summaryEntry

internal fun extractType(entry: LessonEvent): String? {
    val noteFromSummary = entry.summaryEntry.type
    val noteFromDescription = entry.descriptionEntry.type

    return noteFromSummary ?: noteFromDescription
}