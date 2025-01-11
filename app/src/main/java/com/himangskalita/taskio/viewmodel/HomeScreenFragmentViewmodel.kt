package com.himangskalita.taskio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.himangskalita.taskio.data.Task
import com.himangskalita.taskio.di.component.repository.TaskRepository
import javax.inject.Inject

class HomeScreenFragmentViewmodel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val taskList: LiveData<List<Task>> = taskRepository.getTasks().asLiveData()
}