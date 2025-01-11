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
import com.himangskalita.taskio.component.DaggerAppComponent
import com.himangskalita.taskio.databinding.FragmentHomeScreenBinding
import com.himangskalita.taskio.factory.HomeScreenViewModelFactory
import com.himangskalita.taskio.viewmodel.HomeScreenFragmentViewmodel
import javax.inject.Inject


class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding

    private lateinit var homeScreenFragmentViewmodel: HomeScreenFragmentViewmodel

    @Inject
    lateinit var homeScreenViewModelFactory: HomeScreenViewModelFactory

    private lateinit var taskAdapter: TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        (requireActivity().application as TaskioApplication).appComponent.inject(this)

        homeScreenFragmentViewmodel = ViewModelProvider(this, homeScreenViewModelFactory)[HomeScreenFragmentViewmodel::class.java]

        taskAdapter = TaskAdapter()

        binding.fgHsRvTaskList.adapter = taskAdapter
        binding.fgHsRvTaskList.layoutManager = LinearLayoutManager(requireContext())

        homeScreenFragmentViewmodel.taskList.observe(viewLifecycleOwner) { taskList ->

            taskAdapter.updateTasks(taskList)
        }

        return binding.root
    }
}