package xyz.ottersbach.hwrscheduler.icsparser.internal.evententries

internal data class SummaryEntry(val string: String)

internal val SummaryEntry.parts
    get() = string
        .substring("SUMMARY:".length)
        .split("\\;")

internal val SummaryEntry.type: String?
    get() = extractPartOrNull(0)

internal val SummaryEntry.module: String?
    get() = extractPartOrNull(1)

internal val SummaryEntry.lecturers: String?
    get() = extractPartOrNull(2)

internal val SummaryEntry.note: String?
    get() = extractPartOrNull(3)

private fun SummaryEntry.extractPartOrNull(index: Int): String? =
    parts
        .getOrNull(index)
        ?.trim()
        ?.takeIf { it.isNotBlank() && it != "-" }
