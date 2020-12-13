package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.*

internal fun buildEvent(
    uidEntry: UidEntry = UidEntry("UID:sked.de956040"),
    summaryEntry: SummaryEntry = SummaryEntry("SUMMARY:La\\;IT2101-Labor SWE I: Gruppe 1 + 2\\;Kretzmer\\;Prüfung Online"),
    locationEntry: LocationEntry = LocationEntry("LOCATION:"),
    descriptionEntry: DescriptionEntry = DescriptionEntry(
        """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: Kretzmer\nRaum: -\nAnmerkung: Prüfung Online\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    ),
    startTimeEntry: StartTimeEntry = StartTimeEntry("DTSTART;TZID=Europe/Berlin:20201102T133000"),
    endTimeEntry: EndTimeEntry = EndTimeEntry("DTEND;TZID=Europe/Berlin:20201102T164500")
) = LessonEvent(
    """
    |BEGIN:VEVENT
    |DTSTAMP:20201108T201145Z
    |TRANSP:OPAQUE
    |SEQUENCE:0
    |${uidEntry.string}
    |${summaryEntry.string}
    |${locationEntry.string}
    |${descriptionEntry.string}
    |${startTimeEntry.string}
    |${endTimeEntry.string}
    |PRIORITY:5
    |CLASS:PUBLIC
""".trimMargin()
)