package com.himangskalita.taskio.di.component.repository

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.himangskalita.taskio.data.Task
import com.himangskalita.taskio.utils.LocalDateDeserializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

interface TaskRepository {

    fun getTasks(): Flow<List<Task>>
    fun addTask(task: Task)
    fun modifyTask(task: Task)
    fun deleteTask(taskId: Int)
}

class TaskRepositoryImpl @Inject constructor(

    private val context: Context
) : TaskRepository {

    private val _taskList = MutableStateFlow<List<Task>>(emptyList())

    init {

        loadTasksFromJson()
    }

    private fun loadTasksFromJson() {

        val jsonDataString =
            context.assets.open("task_data.json").bufferedReader().use { lines -> lines.readText() }

        val dataType = object : TypeToken<List<Task>>() {}.type

        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateDeserializer())
            .create()

        val taskData: List<Task> = gson.fromJson(jsonDataString, dataType)

        _taskList.value = taskData
    }

    override fun getTasks(): Flow<List<Task>> {

        return _taskList.map { it.sortedBy { task -> task.taskId } }
    }

    override fun addTask(task: Task) {

        val newTask = task.copy(taskId = uniqueTaskID())
        _taskList.update { it + newTask }
    }

    private fun uniqueTaskID(): Int {

        return _taskList.value.maxOfOrNull { it.taskId }?.plus(1) ?: 1
    }

    override fun modifyTask(task: Task) {

        if (_taskList.value.none { it.taskId == task.taskId }) {

            throw IllegalArgumentException("Task with id: ${task.taskId} doesn't exist")
        }

        _taskList.update { updateTaskList ->

            updateTaskList.map { currentTask ->

                if (currentTask.taskId == task.taskId) {

                    task
                } else {
                    currentTask
                }
            }
        }
    }

    override fun deleteTask(taskId: Int) {

        _taskList.update { deleteTaskList ->

            deleteTaskList.filter { deleteTask ->

                deleteTask.taskId != taskId
            }
        }

        _taskList.value = _taskList.value.filter { it.taskId != taskId }
    }
}