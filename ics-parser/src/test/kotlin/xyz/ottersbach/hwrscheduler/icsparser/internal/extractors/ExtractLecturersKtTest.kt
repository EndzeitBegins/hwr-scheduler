package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.DescriptionEntry
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.SummaryEntry

internal class ExtractLecturersKtTest {

    private val summaryEntryWithoutThirdPart = SummaryEntry("SUMMARY:La\\;IT2101-Labor SWE I: Gruppe 1 + 2")
    private val summaryEntryWithBlankThirdPart = SummaryEntry("SUMMARY:La\\;IT2101-Labor SWE I: Gruppe 1 + 2\\;  ")
    private val summaryEntryWithDashAsThirdPart = SummaryEntry("SUMMARY:La\\;IT2101-Labor SWE I: Gruppe 1 + 2\\; - ")
    private val summaryEntryWithAValueAsThirdPart =
        SummaryEntry("SUMMARY:La\\;IT2101-Labor SWE I: Gruppe 1 + 2\\; example lecturer summary \\; Online")

    private val descriptionEntryWithoutDozentPart = DescriptionEntry(
        """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nRau
        |	m: -\nAnmerkung: Online\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )
    private val descriptionEntryWithBlankDozentPart = DescriptionEntry(
        """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent:  \nRaum: -\nAnmerkung: Online\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )
    private val descriptionEntryWithDashAsDozentPart = DescriptionEntry(
        """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: - \nRaum: -\nAnmerkung: Online\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )
    private val descriptionEntryWithAValueAsDozentPart = DescriptionEntry(
        """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: example lecturer description\nRaum: -\nAnmerkung: Online\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )

    @Nested
    inner class WhenSummaryEntryHasNotAThirdPart {
        private val summaryEntry = summaryEntryWithoutThirdPart

        @Nested
        inner class WhenDescriptionEntryHasNotADozentPart {
            private val descriptionEntry = descriptionEntryWithoutDozentPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers of value emptyList`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasABlankDozentPart {
            private val descriptionEntry = descriptionEntryWithBlankDozentPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers of value emptyList`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasDashAsDozentPart {
            private val descriptionEntry = descriptionEntryWithDashAsDozentPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers of value emptyList`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasAValueAsDozentPart {
            private val descriptionEntry = descriptionEntryWithAValueAsDozentPart

            @Test
            internal fun `returns value from description entry`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers value based on description entry`(entry)
            }
        }
    }

    @Nested
    inner class WhenSummaryEntryHasABlankThirdPart {
        private val summaryEntry = summaryEntryWithBlankThirdPart

        @Nested
        inner class WhenDescriptionEntryHasNotADozentPart {
            private val descriptionEntry = descriptionEntryWithoutDozentPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers of value emptyList`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasABlankDozentPart {
            private val descriptionEntry = descriptionEntryWithBlankDozentPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers of value emptyList`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasDashAsDozentPart {
            private val descriptionEntry = descriptionEntryWithDashAsDozentPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers of value emptyList`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasAValueAsDozentPart {
            private val descriptionEntry = descriptionEntryWithAValueAsDozentPart

            @Test
            internal fun `returns value from description entry`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers value based on description entry`(entry)
            }
        }


    }

    @Nested
    inner class WhenSummaryEntryHasDashAsThirdPart {
        private val summaryEntry = summaryEntryWithDashAsThirdPart

        @Nested
        inner class WhenDescriptionEntryHasNotADozentPart {
            private val descriptionEntry = descriptionEntryWithoutDozentPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers of value emptyList`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasABlankDozentPart {
            private val descriptionEntry = descriptionEntryWithBlankDozentPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers of value emptyList`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasDashAsDozentPart {
            private val descriptionEntry = descriptionEntryWithDashAsDozentPart

            @Test
            internal fun `returns null`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers of value emptyList`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasAValueAsDozentPart {
            private val descriptionEntry = descriptionEntryWithAValueAsDozentPart

            @Test
            internal fun `returns value from description entry`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers value based on description entry`(entry)
            }
        }


    }

    @Nested
    inner class WhenSummaryEntryHasAValueAsThirdPart {
        private val summaryEntry = summaryEntryWithAValueAsThirdPart

        @Nested
        inner class WhenDescriptionEntryHasNotADozentPart {
            private val descriptionEntry = descriptionEntryWithoutDozentPart

            @Test
            internal fun `returns value from summary entry`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers value based on summary entry`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasABlankDozentPart {
            private val descriptionEntry = descriptionEntryWithBlankDozentPart

            @Test
            internal fun `returns value from summary entry`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers value based on summary entry`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasDashAsDozentPart {
            private val descriptionEntry = descriptionEntryWithDashAsDozentPart

            @Test
            internal fun `returns value from summary entry`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers value based on summary entry`(entry)
            }
        }

        @Nested
        inner class WhenDescriptionEntryHasAValueAsDozentPart {
            private val descriptionEntry = descriptionEntryWithAValueAsDozentPart

            @Test
            internal fun `returns value from summary entry`() {
                val entry = buildEvent(
                    summaryEntry = summaryEntry,
                    descriptionEntry = descriptionEntry
                )

                `assertThat entry yields lecturers value based on summary entry`(entry)
            }
        }
    }

    @Test
    internal fun `supports multiple lecturers in summary entry`() {
        val summaryEntry = SummaryEntry("SUMMARY:La\\;IT2101-Labor SWE I: Gruppe 1 + 2\\; lecturer sum 1\\, lecturer sum 2 \\; Online")
        val entry = buildEvent(
            summaryEntry = summaryEntry,
            descriptionEntry = descriptionEntryWithoutDozentPart
        )

        val result = extractLecturers(entry)

        assertThat(result, equalTo(listOf("lecturer sum 1", "lecturer sum 2")))
    }

    @Test
    internal fun `supports multiple lecturers in description entry`() {
        val descriptionEntry = DescriptionEntry(
            """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: lecturer desc 1\, lecturer desc 2 \nRaum: -\nAnmerkung: Online\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
        )
        val entry = buildEvent(
            summaryEntry = summaryEntryWithoutThirdPart,
            descriptionEntry = descriptionEntry
        )

        val result = extractLecturers(entry)

        assertThat(result, equalTo(listOf("lecturer desc 1", "lecturer desc 2")))
    }

    @Test
    internal fun `supports suffixed first name abbreviations in summary entry`() {
        val summaryEntry = SummaryEntry(
            "SUMMARY:La\\;IT2101-Labor SWE I: Gruppe 1 + 2\\; lecturer sum\\, A. \\, B. lecturer sum\\,lecturer sum\\,C.\\; Online"
        )
        val entry = buildEvent(
            summaryEntry = summaryEntry,
            descriptionEntry = descriptionEntryWithoutDozentPart
        )

        val result = extractLecturers(entry)

        assertThat(result, equalTo(listOf("lecturer sum, A.", "B. lecturer sum", "lecturer sum, C.")))
    }

    @Test
    internal fun `supports suffixed first name abbreviations in description entry`() {
        val descriptionEntry = DescriptionEntry(
            """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: lecturer desc\, A. \, B. lecturer desc\,lecturer desc\,C.\nRaum: -\nAnmerkung: Online\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
        )
        val entry = buildEvent(
            summaryEntry = summaryEntryWithoutThirdPart,
            descriptionEntry = descriptionEntry
        )

        val result = extractLecturers(entry)

        assertThat(result, equalTo(listOf("lecturer desc, A.", "B. lecturer desc", "lecturer desc, C.")))
    }

    private fun `assertThat entry yields lecturers of value emptyList`(entry: LessonEvent) {
        val result = extractLecturers(entry)

        assertThat(result, equalTo(emptyList()))
    }

    private fun `assertThat entry yields lecturers value based on summary entry`(entry: LessonEvent) {
        val result = extractLecturers(entry)

        assertThat(result, equalTo(listOf("example lecturer summary")))
    }

    private fun `assertThat entry yields lecturers value based on description entry`(entry: LessonEvent) {
        val result = extractLecturers(entry)

        assertThat(result, equalTo(listOf("example lecturer description")))
    }

}