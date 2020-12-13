package xyz.ottersbach.hwrscheduler.icsparser.internal.evententries

internal data class LocationEntry(val string: String)

internal val LocationEntry.room: String?
    get() = string
        .replace("^LOCATION:\\s*".toRegex(), "")
        .takeIf { it.isNotBlank() && it.trim() != "-" }
