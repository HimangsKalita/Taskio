package com.himangskalita.taskio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.himangskalita.taskio.data.Task
import com.himangskalita.taskio.databinding.FragmentHomeScreenTaskLayoutBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TaskAdapter (

    val taskList: MutableList<Task> = mutableListOf()
) : RecyclerView.Adapter<TaskAdapter.TaskHolderClass>() {

    inner class TaskHolderClass(val binding: FragmentHomeScreenTaskLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateTasks(newTaskList: List<Task>) {

        taskList.clear()
        taskList.addAll(newTaskList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolderClass {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentHomeScreenTaskLayoutBinding.inflate(layoutInflater, parent, false)

        return TaskHolderClass(binding)
    }

    override fun onBindViewHolder(holder: TaskHolderClass, position: Int) {

        val task = taskList[position]

        holder.binding.apply {

            tlTvTaskTitle.text = task.taskName
            tlCbTaskisCompleted.isChecked = task.taskCompleted
            tlTvTaskDescription.text = task.taskDescription

            tlTvTaskcreationDate.text = parseDate(task.creationDate)
        }
    }

    private fun parseDate(localdate: LocalDate) : String {

        val dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, YYYY")

        return localdate.format(dateTimeFormatter)
    }

    override fun getItemCount(): Int {

        return taskList.size
    }
}