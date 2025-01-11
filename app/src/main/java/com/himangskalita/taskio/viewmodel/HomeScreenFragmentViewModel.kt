package com.himangskalita.taskio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.himangskalita.taskio.data.Task
import com.himangskalita.taskio.di.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenFragmentViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val taskList: LiveData<List<Task>>
    get() = taskRepository.getTasks().asLiveData()

}