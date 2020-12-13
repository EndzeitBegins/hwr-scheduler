package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.uid
import xyz.ottersbach.hwrscheduler.icsparser.internal.uidEntry

internal fun extractUid(entry: LessonEvent) = entry.uidEntry.uid