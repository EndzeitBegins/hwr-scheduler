package xyz.ottersbach.hwrscheduler.icsparser.internal.evententries

internal data class EndTimeEntry(val string: String)

private val EndTimeEntry.regexResult: MatchResult
    get() = requireNotNull(regex.matchEntire(string))

internal val EndTimeEntry.timeZone: String?
    get() = regexResult.groups[2]?.value

internal val EndTimeEntry.time: String
    get() = regexResult.groupValues[3]

private val regex = "DTEND(;TZID=(.+))?:(\\d{8}T\\d{6})".toRegex()