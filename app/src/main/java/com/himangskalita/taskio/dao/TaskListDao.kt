package com.himangskalita.taskio.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.himangskalita.taskio.data.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskListDao {

    @Upsert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM Task ORDER BY taskName ASC")
    fun taskOrderByName(): Flow<List<Task>>

    @Query("SELECT * FROM Task ORDER BY isChecked DESC")
    fun taskOrderedByIsChecked(): Flow<List<Task>>
}