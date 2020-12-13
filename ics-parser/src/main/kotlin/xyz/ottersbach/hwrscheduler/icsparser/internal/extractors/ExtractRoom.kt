package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.descriptionEntry
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.room
import xyz.ottersbach.hwrscheduler.icsparser.internal.locationEntry

internal fun extractRoom(entry: LessonEvent): String? {
    val roomFromLocation = entry.locationEntry.room
    val roomFromDescription = entry.descriptionEntry.room

    return roomFromLocation ?: roomFromDescription
}