package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.descriptionEntry
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.room
import xyz.ottersbach.hwrscheduler.icsparser.internal.locationEntry

internal fun extractRooms(entry: LessonEvent): List<String> {
    val roomFromDescription = entry.descriptionEntry.room
    val roomFromLocation = entry.locationEntry.room

    val roomsString = roomFromLocation ?: roomFromDescription

    return roomsString
        ?.split("\\,")
        ?.map(String::trim) ?: emptyList()
}