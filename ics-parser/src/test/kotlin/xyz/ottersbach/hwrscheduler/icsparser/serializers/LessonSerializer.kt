package xyz.ottersbach.hwrscheduler.icsparser.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import xyz.ottersbach.hwrscheduler.icsparser.Lesson
import java.time.Instant

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Lesson::class)
object LessonSerializer : KSerializer<Lesson> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Lesson") {
        element<String>("uid")
        element<String>("start")
        element<String>("end")
        element<String>("module")
        element<List<String>>("lecturers")
        element<String?>("room", isOptional = true)
        element<String?>("type", isOptional = true)
        element<String?>("note", isOptional = true)
    }

    override fun serialize(encoder: Encoder, value: Lesson) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.uid)
            encodeSerializableElement(descriptor, 1, InstantSerializer, value.start)
            encodeSerializableElement(descriptor, 2, InstantSerializer, value.end)
            encodeStringElement(descriptor, 3, value.module)
            encodeSerializableElement(descriptor, 4, ListSerializer(String.serializer()), value.lecturers)
            encodeNullableSerializableElement(descriptor, 5, String.serializer(), value.room)
            encodeNullableSerializableElement(descriptor, 6, String.serializer(), value.type)
            encodeNullableSerializableElement(descriptor, 7, String.serializer(), value.note)
        }
    }

    override fun deserialize(decoder: Decoder): Lesson {
        return decoder.decodeStructure(descriptor) {
            var uid: String? = null
            var start: Instant? = null
            var end: Instant? = null
            var module: String? = null
            var lecturers: List<String> = emptyList()
            var room: String? = null
            var type: String? = null
            var note: String? = null

            loop@ while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    DECODE_DONE -> break@loop

                    0 -> uid = decodeStringElement(descriptor, 0)
                    1 -> start = decodeSerializableElement(descriptor, 1, InstantSerializer)
                    2 -> end = decodeSerializableElement(descriptor, 2, InstantSerializer)
                    3 -> module = decodeStringElement(descriptor, 3)
                    4 -> lecturers = decodeSerializableElement(descriptor, 4, ListSerializer(String.serializer()))
                    5 -> room = decodeNullableSerializableElement(descriptor, 5, String.serializer().nullable)
                    6 -> type = decodeNullableSerializableElement(descriptor, 6, String.serializer().nullable)
                    7 -> note = decodeNullableSerializableElement(descriptor, 7, String.serializer().nullable)

                    else -> throw SerializationException("Unexpected index $index")
                }
            }

            Lesson(
                requireNotNull(uid),
                requireNotNull(start),
                requireNotNull(end),
                requireNotNull(module),
                lecturers,
                room,
                type,
                note
            )
        }
    }
}