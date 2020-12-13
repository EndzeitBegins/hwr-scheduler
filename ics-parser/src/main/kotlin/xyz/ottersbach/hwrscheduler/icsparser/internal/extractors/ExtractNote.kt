package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.descriptionEntry
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.note

internal fun extractNote(entry: LessonEvent): String? {
    val noteFromDescription = entry.descriptionEntry.note

    return noteFromDescription?.replace("\\", "")
}