package com.himangskalita.taskio.data

data class TaskState(
    val taskList: List<Task> = emptyList(),
    val taskName: String = "",
    val isChecked: Boolean = false,
    val isAddingTask: Boolean = false,
    val sortType: SortType = SortType.TASK_NAME
)
