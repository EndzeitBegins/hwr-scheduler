package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import com.natpryce.hamkrest.absent
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.DescriptionEntry
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.SummaryEntry

internal class ExtractTypeKtTest {
    private val summaryEntryWithBlankFirstPart = SummaryEntry("SUMMARY:\\;IT2101-Labor SWE I: Gruppe 1 + 2\\;Kretzmer\\; Online")
    private val summaryEntryWithDashAsFirstPart = SummaryEntry("SUMMARY: -\\;IT2101-Labor SWE I: Gruppe 1 + 2\\;Kretzmer\\; Online")
    private val summaryEntryWithAValueAsFirstPart =
        SummaryEntry("SUMMARY: example type summary\\;IT2101-Labor SWE I: Gruppe 1 + 2\\;Kretzmer\\; Online")

    private val descriptionEntryWithoutArtPart = DescriptionEntry(
        """
        |DESCRIPTION:Veranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: Kretzmer\nRaum: -\nAnmerkung: Online\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )
    private val descriptionEntryWithBlankArtPart = DescriptionEntry(
        """
        |DESCRIPTION:Art:  \nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: Kretzmer\nRaum: -\nAnmerkung: Online\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )
    private val descriptionEntryWithDashAsArtPart = DescriptionEntry(
        """
        |DESCRIPTION:Art: -\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: Kretzmer\nRaum: -\nAnmerkung: Online\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )
    private val descriptionEntryWithAValueAsArtPart = DescriptionEntry(
        """
        |DESCRIPTION:Art: example type description\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: Kretzmer\nRaum: -\nAnmerkung: Online\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )

    @Nested
    inner class WhenSummaryEntryHasABlankFirstPart {
        private val summaryEntry = summaryEntryWithBlankFirstPart

        @Nested
        inner class WhenDescriptionEntryHasNotAnArtPart {
            private val descriptionEntry = descriptionEntryWithoutArtPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields type of value null`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasABlankArtPart {
            private val descriptionEntry = descriptionEntryWithBlankArtPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields type of value null`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasDashAsArtPart {
            private val descriptionEntry = descriptionEntryWithDashAsArtPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields type of value null`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasAValueAsArtPart {
            private val descriptionEntry = descriptionEntryWithAValueAsArtPart

            @Test
            internal fun `returns value from description entry`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields type value based on description entry`(entry)
            }
        }


    }

    @Nested
    inner class WhenSummaryEntryHasDashAsFirstPart {
        private val summaryEntry = summaryEntryWithDashAsFirstPart

        @Nested
        inner class WhenDescriptionEntryHasNotAnArtPart {
            private val descriptionEntry = descriptionEntryWithoutArtPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields type of value null`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasABlankArtPart {
            private val descriptionEntry = descriptionEntryWithBlankArtPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields type of value null`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasDashAsArtPart {
            private val descriptionEntry = descriptionEntryWithDashAsArtPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields type of value null`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasAValueAsArtPart {
            private val descriptionEntry = descriptionEntryWithAValueAsArtPart

            @Test
            internal fun `returns value from description entry`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields type value based on description entry`(entry)
            }
        }


    }

    @Nested
    inner class WhenSummaryEntryHasAValueAsFirstPart {
        private val summaryEntry = summaryEntryWithAValueAsFirstPart

        @Nested
        inner class WhenDescriptionEntryHasNotAnArtPart {
            private val descriptionEntry = descriptionEntryWithoutArtPart

            @Test
            internal fun `returns value from summary entry`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields type value based on summary entry`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasABlankArtPart {
            private val descriptionEntry = descriptionEntryWithBlankArtPart

            @Test
            internal fun `returns value from summary entry`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields type value based on summary entry`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasDashAsArtPart {
            private val descriptionEntry = descriptionEntryWithDashAsArtPart

            @Test
            internal fun `returns value from summary entry`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields type value based on summary entry`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasAValueAsArtPart {
            private val descriptionEntry = descriptionEntryWithAValueAsArtPart

            @Test
            internal fun `returns value from summary entry`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields type value based on summary entry`(entry)
            }
        }


    }

    private fun `assertThat entry yields type of value null`(entry: LessonEvent) {
        val result = extractType(entry)

        assertThat(result, absent())
    }

    private fun `assertThat entry yields type value based on summary entry`(entry: LessonEvent) {
        val result = extractType(entry)

        assertThat(result, equalTo("example type summary"))
    }

    private fun `assertThat entry yields type value based on description entry`(entry: LessonEvent) {
        val result = extractType(entry)

        assertThat(result, equalTo("example type description"))
    }
}