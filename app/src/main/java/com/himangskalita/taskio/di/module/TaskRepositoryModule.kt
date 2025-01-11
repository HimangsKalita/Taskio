package com.himangskalita.taskio.di.module

import android.content.Context
import com.himangskalita.taskio.di.repository.TaskRepository
import com.himangskalita.taskio.di.repository.TaskRepositoryIml
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class TaskRepositoryModule {

    @Provides
    fun getTaskRepository(@ApplicationContext context: Context) : TaskRepository {

        return TaskRepositoryIml(context)
    }
}