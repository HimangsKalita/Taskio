package com.himangskalita.taskio.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Task(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val taskName: String,
    var isChecked: Boolean,
//    val dueDate: LocalDate,
//    val dueTime: LocalTime
)
