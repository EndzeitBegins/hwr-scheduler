
package xyz.ottersbach.hwrscheduler.icsparser

import java.time.Instant

public data class Lesson(
    val uid: String,
    val start: Instant,
    val end: Instant,
    val module: String,
    val lecturers: List<String>,
    val room: String?,
    val type: String?,
    val note: String?
)