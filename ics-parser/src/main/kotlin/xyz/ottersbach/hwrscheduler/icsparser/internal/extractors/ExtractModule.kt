package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.descriptionEntry
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.module
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.note
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.parts
import xyz.ottersbach.hwrscheduler.icsparser.internal.summaryEntry

internal fun extractModule(entry: LessonEvent): String {
    val moduleFromSummary = entry.summaryEntry.module
    val moduleFromDescription = entry.descriptionEntry.module

    return requireNotNull(moduleFromSummary ?: moduleFromDescription)
}