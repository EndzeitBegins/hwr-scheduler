package xyz.ottersbach.hwrscheduler.icsparser.internal.evententries

internal data class UidEntry(val string: String)

internal val UidEntry.uid
    get() = string.substring("UID:".length)