package com.himangskalita.taskio.utils

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHandler : SQLiteOpenHelper() {

    private val version = 1
    private val name = "taskDatabse"
    private val tableName = "taskTable"
    

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}