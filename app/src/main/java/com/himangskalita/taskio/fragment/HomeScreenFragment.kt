package com.himangskalita.taskio.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.himangskalita.taskio.TaskioApplication
import com.himangskalita.taskio.adapter.TaskAdapter
import com.himangskalita.taskio.databinding.FragmentHomeScreenBinding
import com.himangskalita.taskio.viewmodel.HomeScreenFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var homeScreenFragmentViewModel: HomeScreenFragmentViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        homeScreenFragmentViewModel = ViewModelProvider(this)[HomeScreenFragmentViewModel::class]

        taskAdapter = TaskAdapter()

        binding.fgHsRvTaskList.adapter = taskAdapter
        binding.fgHsRvTaskList.layoutManager = LinearLayoutManager(requireContext())

        homeScreenFragmentViewModel.taskList.observe(viewLifecycleOwner) { taskList ->

            taskAdapter.updateTask(taskList.toList())
        }

        return binding.root
    }
}