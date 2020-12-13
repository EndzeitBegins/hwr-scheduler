# ics-parser

A library to deserialize and parse .ics-files from the
[HWR's moodle site](https://moodle.hwr-berlin.de/fb2-stundenplan/stundenplan.php).

### example usage

To parse a .ics-file retrieved from the HWR's moodle site, 
simply pass the file's content to the function `parse` provided by this library,
as such:

``` kotlin
    // example input - normally retrived from the official HWR website
    val input = """
        BEGIN:VCALENDAR
        BEGIN:VEVENT
        DTSTAMP:20201108T201145Z
        TRANSP:OPAQUE
        SEQUENCE:0
        UID:sked.de956040
        SUMMARY:La\;IT2101-Labor SWE I: Gruppe 1 + 2\;Kretzmer\;Prüfung Online
        LOCATION:
        DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        	ent: Kretzmer\nRaum: -\nAnmerkung: Prüfung Online\nPause: inkl. 15 min Pa
        	use\nVeranstaltungsuntergruppe: -
        DTSTART;TZID=Europe/Berlin:20201102T133000
        DTEND;TZID=Europe/Berlin:20201102T164500
        PRIORITY:5
        CLASS:PUBLIC
        END:VEVENT
        END:VCALENDAR
    """.trimIndent()

    // simple call to library function
    val lessons = parse(input)

    // will yield 
    // [Lesson(
    //      uid=sked.de956040, 
    //      start=2020-11-02T13:30:00Z,
    //      end=2020-11-02T16:45:00Z, 
    //      module=IT2101-Labor SWE I: Gruppe 1 + 2, 
    //      lecturers=[Kretzmer], 
    //      room=null, 
    //      type=La, 
    //      note=Prüfung Online
    // )]
    lessons
```