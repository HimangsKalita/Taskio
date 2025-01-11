package com.himangskalita.taskio.di.repository

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.himangskalita.taskio.data.Task
import com.himangskalita.taskio.utils.JsonDateDeserializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

interface TaskRepository {

    fun getTasks() : Flow<List<Task>>
    fun addTask(task: Task)
    fun modifyTask(task: Task)
    fun deleteTask(task: Task)
}

@Singleton
class TaskRepositoryIml @Inject constructor(
    private val context: Context
) : TaskRepository {

    private val taskList = MutableStateFlow<List<Task>>(emptyList())

    init {

        loadTasksFromJson()
    }

    private fun loadTasksFromJson() {

        val jsonDataString = context.assets.open("task_data.json").bufferedReader().use { lines ->

            lines.readText()
        }

        val dataType = object : TypeToken<List<Task>>() {}.type
        val gson = GsonBuilder().registerTypeAdapter(LocalDate::class.java, JsonDateDeserializer()).create()

        val data: List<Task> = gson.fromJson(jsonDataString, dataType)

        taskList.value = data
    }

    override fun getTasks(): Flow<List<Task>> {

        return taskList.map { tasks -> tasks.sortedWith(compareBy(

            {it.creationDate.year},
            {it.creationDate.month},
            {it.creationDate.dayOfMonth},
        ))
        }
    }

    override fun addTask(task: Task) {

        taskList.update { it+task }
    }

    override fun modifyTask(task: Task) {

        taskList.update { it.map { modifyTask -> if (modifyTask.taskId == task.taskId) task else modifyTask } }
    }

    override fun deleteTask(task: Task) {

        taskList.update { it.filter { deleteTask -> deleteTask.taskId != task.taskId } }
    }
}