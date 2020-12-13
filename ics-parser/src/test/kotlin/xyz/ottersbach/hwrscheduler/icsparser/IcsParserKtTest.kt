@file:UseSerializers(InstantSerializer::class, LessonSerializer::class)

package xyz.ottersbach.hwrscheduler.icsparser

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import xyz.ottersbach.hwrscheduler.icsparser.serializers.InstantSerializer
import xyz.ottersbach.hwrscheduler.icsparser.serializers.LessonSerializer
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

internal class IcsParserKtTest {

    @TestFactory
    internal fun `parses data from 2020-12-12 correctly`(): List<DynamicTest> {
        return `assertThat data is parsed correctly`("20201212")
    }

    private fun `assertThat data is parsed correctly`(folderName: String): List<DynamicTest> {
        val basePath = Paths.get("src/test/resources/ics-files/$folderName")

        val icsFiles = basePath.toFile().walk()
            .filter { it.isFile }
            .filter { it.extension == "ics" }
            .toList()

        return icsFiles.map { icsFile ->
            val testName = icsFile.nameWithoutExtension
            val expectedResultFileName = "${testName}.json"

            dynamicTest(testName) {
                val input = icsFile.readText()
                val expectedResultFile = basePath.resolve(expectedResultFileName).toFile()

                assertThat(expectedResultFile, File::exists) {
                    "expected result file $expectedResultFileName does NOt exist!"
                }

                val expectedResultFileContent = expectedResultFile.readText()
                val expectedResult = Json.decodeFromString<ExpectedLessons>(expectedResultFileContent)

                val result = parse(input)

                assertThat(result, equalTo(expectedResult.lessons))
            }
        }
    }
}

@Serializable
data class ExpectedLessons(
    val lessons: List<Lesson>
)
