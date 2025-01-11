package com.himangskalita.taskio.di.component.module

import android.content.Context
import com.himangskalita.taskio.di.component.repository.TaskRepository
import com.himangskalita.taskio.di.component.repository.TaskRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class TaskRepositoryModule {

    @Provides
    fun getTaskRepositoryImlService(context: Context) : TaskRepository {
        return TaskRepositoryImpl(context)
    }
}