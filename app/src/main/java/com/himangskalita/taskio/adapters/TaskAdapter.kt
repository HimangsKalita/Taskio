package com.himangskalita.taskio.adapters

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.himangskalita.taskio.R
import com.himangskalita.taskio.data.Task
import com.himangskalita.taskio.databinding.TodoItemBinding
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.Collections


class TaskAdapter(
    val taskList: MutableList<Task>,
    val archiveList: MutableList<Task>,
    val context: Context
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(layoutInflater, parent, false)

        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val taskListItem = taskList[position]

        holder.binding.apply {

            cbItemTodoCompleted.isChecked = taskListItem.isChecked
            tvItemTodoTitle.text = taskListItem.taskName

            if (cbItemTodoCompleted.isChecked) {

                tvItemTodoTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }

        holder.binding.cbItemTodoCompleted.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {


                holder.binding.tvItemTodoTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }else {

                holder.binding.tvItemTodoTitle.paintFlags = 0
            }
        }
    }

    override fun getItemCount(): Int {

        return taskList.size
    }

    fun getSimpleCallback(): ItemTouchHelper.SimpleCallback {

        return SimpleCallback()
    }

    inner class SimpleCallback : ItemTouchHelper.SimpleCallback(
        UP or DOWN,
        LEFT or RIGHT
    ) {


        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {

            Collections.swap(
                taskList,
                viewHolder.bindingAdapterPosition,
                target.bindingAdapterPosition
            )
            notifyItemMoved(viewHolder.bindingAdapterPosition, target.bindingAdapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            val position = viewHolder.bindingAdapterPosition

            when (direction) {

                LEFT -> {

                    val taskRemoved = taskList[position]
                    taskList.removeAt(position)
                    notifyItemRemoved(position)

                    Snackbar.make(viewHolder.itemView, "Task Deleted", Snackbar.LENGTH_LONG)
                        .setTextColor(ContextCompat.getColor(context, R.color.white))
                        .setAction("UNDO") {

                            taskList.add(position, taskRemoved)
                            notifyItemInserted(position)
                        }
                        .setActionTextColor(ContextCompat.getColor(context, R.color.lightBlue))
                        .show()
                }

                RIGHT -> {

                    val taskArchived = taskList[position]
                    taskList.removeAt(position)
                    notifyItemRemoved(position)
                    archiveList.add(taskArchived)

                    Snackbar.make(viewHolder.itemView, "Task Archived", Snackbar.LENGTH_LONG)
                        .setTextColor(ContextCompat.getColor(context, R.color.white))
                        .setAction("UNDO") {

                            taskList.add(position, taskArchived)
                            notifyItemInserted(position)
                            archiveList.removeAt(archiveList.size - 1)
                        }
                        .setActionTextColor(ContextCompat.getColor(context, R.color.lightBlue))
                        .show()
                }
            }
        }

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {

            RecyclerViewSwipeDecorator.Builder(
                c,
                recyclerView,
                viewHolder,
                dX,
                dY,
                actionState,
                isCurrentlyActive
            )
                .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, R.color.red))
                .addSwipeLeftActionIcon(R.drawable.ic_delete_add_task)
                .setSwipeLeftActionIconTint(ContextCompat.getColor(context, R.color.white))
                .addSwipeLeftLabel("Delete")
                .setSwipeLeftLabelColor(ContextCompat.getColor(context, R.color.white))
                .setSwipeLeftLabelTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)

                .addSwipeRightBackgroundColor(ContextCompat.getColor(context, R.color.green))
                .addSwipeRightActionIcon(R.drawable.ic_archive_task_archive)
                .setSwipeRightActionIconTint(ContextCompat.getColor(context, R.color.white))
                .addSwipeRightLabel("Archive")
                .setSwipeRightLabelColor(ContextCompat.getColor(context, R.color.white))
                .setSwipeRightLabelTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)

                .addCornerRadius(TypedValue.COMPLEX_UNIT_DIP, 20)
                .create()
                .decorate()

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }
}