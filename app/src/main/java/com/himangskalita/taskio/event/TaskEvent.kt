package com.himangskalita.taskio.event

import com.himangskalita.taskio.data.SortType
import com.himangskalita.taskio.data.Task

sealed interface TaskEvent {

    data class setTaskName(val taskName: String): TaskEvent
    data class setIsChecked(val isChecked: Boolean): TaskEvent

    object SaveTask: TaskEvent
    object ShowDialog: TaskEvent
    object HideDialog: TaskEvent

    data class SortTask(val sortType: SortType): TaskEvent
    data class DeleteTask(val task: Task): TaskEvent
}