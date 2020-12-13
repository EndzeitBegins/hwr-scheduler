package xyz.ottersbach.hwrscheduler.icsparser.internal.evententries

internal data class StartTimeEntry(val string: String)

private val StartTimeEntry.regexResult: MatchResult
    get() = requireNotNull(regex.matchEntire(string))

internal val StartTimeEntry.timeZone: String?
    get() = regexResult.groups[2]?.value

internal val StartTimeEntry.time: String
    get() = regexResult.groupValues[3]

private val regex = "DTSTART(;TZID=(.+))?:(\\d{8}T\\d{6})".toRegex()