package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.DescriptionEntry

internal class ExtractLecturersKtTest {

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
    inner class WhenDescriptionEntryHasNotADozentPart {
        private val descriptionEntry = descriptionEntryWithoutDozentPart

        @Test
        internal fun `returns null`() {
            val entry = buildEvent(
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
                descriptionEntry = descriptionEntry
            )

            `assertThat entry yields lecturers value based on description entry`(entry)
        }
    }

    @Test
    internal fun `supports multiple lecturers`() {
        val descriptionEntry = DescriptionEntry(
            """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: lecturer desc 1\, lecturer desc 2 \nRaum: -\nAnmerkung: Online\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
        )
        val entry = buildEvent(
            descriptionEntry = descriptionEntry
        )

        val result = extractLecturers(entry)

        assertThat(result, equalTo(listOf("lecturer desc 1", "lecturer desc 2")))
    }

    @Test
    internal fun `supports suffixed first name abbreviations`() {
        val descriptionEntry = DescriptionEntry(
            """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: lecturer desc\, A. \, B. lecturer desc\,lecturer desc\,C.\nRaum: -\nAnmerkung: Online\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
        )
        val entry = buildEvent(
            descriptionEntry = descriptionEntry
        )

        val result = extractLecturers(entry)

        assertThat(result, equalTo(listOf("lecturer desc, A.", "B. lecturer desc", "lecturer desc, C.")))
    }

    private fun `assertThat entry yields lecturers of value emptyList`(entry: LessonEvent) {
        val result = extractLecturers(entry)

        assertThat(result, equalTo(emptyList()))
    }

    private fun `assertThat entry yields lecturers value based on description entry`(entry: LessonEvent) {
        val result = extractLecturers(entry)

        assertThat(result, equalTo(listOf("example lecturer description")))
    }
}