package xyz.ottersbach.hwrscheduler.icsparser.internal.evententries

internal data class DescriptionEntry(val string: String)

internal val DescriptionEntry.parts
    get() = string
        .substring("DESCRIPTION:".length)
        .split("\\n")

internal val DescriptionEntry.type: String?
    get() = extractPartOrNull("Art:")

internal val DescriptionEntry.lecturers: String?
    get() = extractPartOrNull("Dozent:")

internal val DescriptionEntry.room: String?
    get() = extractPartOrNull("Raum:")

internal val DescriptionEntry.module: String?
    get() = extractPartOrNull("Veranstaltung:")

internal val DescriptionEntry.note: String?
    get() = extractPartOrNull("Anmerkung:")

private fun DescriptionEntry.extractPartOrNull(identifier: String): String? =
    parts
        .find { it.startsWith(identifier) }
        ?.replace("^$identifier\\s*".toRegex(), "")
        ?.trim()
        ?.takeIf { it.isNotBlank() && it != "-" }

