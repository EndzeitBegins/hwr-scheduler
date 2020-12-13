package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.descriptionEntry
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.module

internal fun extractModule(entry: LessonEvent): String {
    val moduleFromDescription = entry.descriptionEntry.module

    return requireNotNull(moduleFromDescription)
}