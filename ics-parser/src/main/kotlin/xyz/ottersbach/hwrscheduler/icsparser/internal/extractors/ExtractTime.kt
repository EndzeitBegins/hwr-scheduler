package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.endTimeEntry
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.time
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.timeZone
import xyz.ottersbach.hwrscheduler.icsparser.internal.startTimeEntry
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

internal fun extractStartTime(entry: LessonEvent) =
    extractTime(entry.startTimeEntry.time, entry.startTimeEntry.timeZone)

internal fun extractEndTime(entry: LessonEvent) =
    extractTime(entry.endTimeEntry.time, entry.endTimeEntry.timeZone)

internal fun extractTime(time: String, timeZone: String?): Instant {
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss")
    val localDateTime = LocalDateTime.parse(time, formatter)

    val zoneId = ZoneId.of(timeZone ?: "UTC")

    return localDateTime.atZone(zoneId).toInstant()
}