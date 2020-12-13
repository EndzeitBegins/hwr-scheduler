package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.descriptionEntry
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.type

internal fun extractType(entry: LessonEvent): String? {
    return entry.descriptionEntry.type
}