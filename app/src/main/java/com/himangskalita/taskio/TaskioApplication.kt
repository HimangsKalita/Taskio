package com.himangskalita.taskio

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}