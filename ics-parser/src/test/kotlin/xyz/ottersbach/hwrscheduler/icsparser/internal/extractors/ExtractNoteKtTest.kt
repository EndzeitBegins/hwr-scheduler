package xyz.ottersbach.hwrscheduler.icsparser.internal.extractors

import com.natpryce.hamkrest.absent
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import xyz.ottersbach.hwrscheduler.icsparser.internal.LessonEvent
import xyz.ottersbach.hwrscheduler.icsparser.internal.evententries.DescriptionEntry

internal class ExtractNoteKtTest {

    private val descriptionEntryWithoutAnmerkungPart = DescriptionEntry(
        """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: Kretzmer\nRaum: -\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )
    private val descriptionEntryWithBlankAnmerkungPart = DescriptionEntry(
        """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: Kretzmer\nRaum: -\nAnmerkung: \nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )
    private val descriptionEntryWithDashAsAnmerkungPart = DescriptionEntry(
        """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: Kretzmer\nRaum: -\nAnmerkung: -\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )
    private val descriptionEntryWithAValueAsAnmerkungPart = DescriptionEntry(
        """
        |DESCRIPTION:Art: La\nVeranstaltung: IT2101-Labor SWE I: Gruppe 1 + 2\nDoz
        |	ent: Kretzmer\nRaum: -\nAnmerkung: example note description\nPause: inkl. 15 min Pa
        |	use\nVeranstaltungsuntergruppe: -
    """.trimMargin()
    )


    @Nested
    inner class WhenDescriptionEntryHasNotAnAnmerkungPart {
        private val descriptionEntry = descriptionEntryWithoutAnmerkungPart

        @Test
        internal fun `returns null`() {
            val entry = buildEvent(
                descriptionEntry = descriptionEntry
            )

            `assertThat entry yields note of value null`(entry)
        }
    }

    @Nested
    inner class WhenDescriptionEntryHasABlankAnmerkungPart {
        private val descriptionEntry = descriptionEntryWithBlankAnmerkungPart

        @Test
        internal fun `returns null`() {
            val entry = buildEvent(
                descriptionEntry = descriptionEntry
            )

            `assertThat entry yields note of value null`(entry)
        }
    }

    @Nested
    inner class WhenDescriptionEntryHasDashAsAnmerkungPart {
        private val descriptionEntry = descriptionEntryWithDashAsAnmerkungPart

        @Test
        internal fun `returns null`() {
            val entry = buildEvent(
                descriptionEntry = descriptionEntry
            )

            `assertThat entry yields note of value null`(entry)
        }
    }

    @Nested
    inner class WhenDescriptionEntryHasAValueAsAnmerkungPart {
        private val descriptionEntry = descriptionEntryWithAValueAsAnmerkungPart

        @Test
        internal fun `returns value from description entry`() {
            val entry = buildEvent(
                descriptionEntry = descriptionEntry
            )

            `assertThat entry yields note value based on description entry`(entry)
        }
    }

    private fun `assertThat entry yields note of value null`(entry: LessonEvent) {
        val result = extractNote(entry)

        assertThat(result, absent())
    }

    private fun `assertThat entry yields note value based on description entry`(entry: LessonEvent) {
        val result = extractNote(entry)

        assertThat(result, equalTo("example note description"))
    }
}