package com.himangskalita.taskio

import android.app.Application
import com.himangskalita.taskio.di.component.AppComponent
import com.himangskalita.taskio.component.DaggerAppComponent

class TaskioApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)
    }
}