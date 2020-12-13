package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import com.natpryce.hamkrest.absent
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.DescriptionEntry
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.LocationEntry

internal class ExtractRoomKtTest {
    private val blankLocationEntry = LocationEntry("LOCATION:  ")
    private val dashAsLocationEntry = LocationEntry("LOCATION: - ")
    private val valueAsLocationEntry = LocationEntry("LOCATION: example room location")

    private val descriptionEntryWithoutRaumPart = DescriptionEntry(
        """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: Kretzmer\nRaum: -\nAnmerkung: -\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )
    private val descriptionEntryWithBlankRaumPart = DescriptionEntry(
        """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: Kretzmer\nAnmerkung: -\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )
    private val descriptionEntryWithDashAsRaumPart = DescriptionEntry(
        """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: Kretzmer\nRaum: -\nAnmerkung: -\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )
    private val descriptionEntryWithAValueAsRaumPart = DescriptionEntry(
        """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: Kretzmer\nRaum: example room description\nAnmerkung: example note description\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )

    @Nested
    inner class WhenLocationEntryIsBlank {
        private val locationEntry = blankLocationEntry

        @Nested
        inner class WhenDescriptionEntryHasNotAnRaumPart {
            private val descriptionEntry = descriptionEntryWithoutRaumPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    locationEntry = locationEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields room of value null`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasABlankRaumPart {
            private val descriptionEntry = descriptionEntryWithBlankRaumPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    locationEntry = locationEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields room of value null`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasDashAsRaumPart {
            private val descriptionEntry = descriptionEntryWithDashAsRaumPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    locationEntry = locationEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields room of value null`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasAValueAsRaumPart {
            private val descriptionEntry = descriptionEntryWithAValueAsRaumPart

            @Test
            internal fun `returns value from description entry`() {
                val entry = buildEvent(
                    locationEntry = locationEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields room value based on description entry`(entry)
            }
        }


    }

    @Nested
    inner class WhenLocationEntryIsADash {
        private val locationEntry = dashAsLocationEntry

        @Nested
        inner class WhenDescriptionEntryHasNotAnRaumPart {
            private val descriptionEntry = descriptionEntryWithoutRaumPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    locationEntry = locationEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields room of value null`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasABlankRaumPart {
            private val descriptionEntry = descriptionEntryWithBlankRaumPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    locationEntry = locationEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields room of value null`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasDashAsRaumPart {
            private val descriptionEntry = descriptionEntryWithDashAsRaumPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    locationEntry = locationEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields room of value null`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasAValueAsRaumPart {
            private val descriptionEntry = descriptionEntryWithAValueAsRaumPart

            @Test
            internal fun `returns value from description entry`() {
                val entry = buildEvent(
                    locationEntry = locationEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields room value based on description entry`(entry)
            }
        }


    }

    @Nested
    inner class WhenLocationEntryHasAValue {
        private val locationEntry = valueAsLocationEntry

        @Nested
        inner class WhenDescriptionEntryHasNotARaumPart {
            private val descriptionEntry = descriptionEntryWithoutRaumPart

            @Test
            internal fun `returns value from summary entry`() {
                val entry = buildEvent(
                    locationEntry = locationEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields room value based on location entry`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasABlankRaumPart {
            private val descriptionEntry = descriptionEntryWithBlankRaumPart

            @Test
            internal fun `returns value from summary entry`() {
                val entry = buildEvent(
                    locationEntry = locationEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields room value based on location entry`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasDashAsRaumPart {
            private val descriptionEntry = descriptionEntryWithDashAsRaumPart

            @Test
            internal fun `returns value from summary entry`() {
                val entry = buildEvent(
                    locationEntry = locationEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields room value based on location entry`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasAValueAsRaumPart {
            private val descriptionEntry = descriptionEntryWithAValueAsRaumPart

            @Test
            internal fun `returns value from summary entry`() {
                val entry = buildEvent(
                    locationEntry = locationEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields room value based on location entry`(entry)
            }
        }


    }

    private fun `assertThat entry yields room of value null`(entry: LessonEvent) {
        val result = extractRoom(entry)

        assertThat(result, absent())
    }

    private fun `assertThat entry yields room value based on location entry`(entry: LessonEvent) {
        val result = extractRoom(entry)

        assertThat(result, equalTo("example room location"))
    }

    private fun `assertThat entry yields room value based on description entry`(entry: LessonEvent) {
        val result = extractRoom(entry)

        assertThat(result, equalTo("example room description"))
    }
}