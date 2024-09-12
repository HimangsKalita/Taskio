package com.himangskalita.taskio.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.himangskalita.taskio.dao.TaskListDao

@Database(

    entities = [Task::class],
    version = 1
)
abstract class TaskDataBase: RoomDatabase() {

    abstract val dao: TaskListDao
}