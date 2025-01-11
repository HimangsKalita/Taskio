package com.himangskalita.taskio.data

import java.time.LocalDate

data class Task(
    val taskId: Int,
    val taskName: String,
    val taskDescription: String,
    val taskCompleted: Boolean,
    val creationDate: LocalDate
)
