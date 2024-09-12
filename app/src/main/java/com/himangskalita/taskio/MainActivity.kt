package com.himangskalita.taskio

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowInsetsController
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.himangskalita.taskio.adapters.TaskAdapter
import com.himangskalita.taskio.data.Task
import com.himangskalita.taskio.databinding.ActivityMainBinding


private lateinit var taskList: MutableList<Task>
private val archiveList: MutableList<Task> = mutableListOf()

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupEdgeToEdgeInsets()
        initialization()
        taskList()

    }

    private fun setupEdgeToEdgeInsets() {

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initialization() {

        window.statusBarColor = getColor(R.color.blueDark)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = 0

        setSupportActionBar(binding.toolbar)
    }

    private fun taskList() {

//        val dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy")
//        val timeFormater = DateTimeFormatter.ofPattern("hh:mm a")
//
//        val date = LocalDate.parse("09/09/2024", dateFormater)
//        val time = LocalTime.parse("09:30 AM", timeFormater)

        taskList = mutableListOf(

            Task(0, "Buy Groceries \uD83E\uDD51", false),
            Task(1, "Study DSA", false),
            Task(2, "Call a friend", true),
            Task(3, "Repair device", false),
            Task(4, "Charge camera", false),
            Task(5, "Exercise \uD83D\uDCAA", true),
            Task(6, "Play game \uD83C\uDFAE", false),
            Task(7, "Cardio ", false),
            Task(8, "Floss teeth", false),
            Task(0, "Buy Groceries \uD83E\uDD51", false),
            Task(1, "Study DSA", false),
            Task(2, "Call a friend", true),
            Task(3, "Repair device", false),
            Task(4, "Charge camera", false),
            Task(5, "Exercise \uD83D\uDCAA", true),
            Task(6, "Play game \uD83C\uDFAE", false),
            Task(7, "Cardio ", false),
            Task(8, "Floss teeth", false),
        )

        taskAdapter = TaskAdapter(taskList, archiveList, this)

        binding.rvTodoList.adapter = taskAdapter
        binding.rvTodoList.layoutManager = LinearLayoutManager(this)

        binding.fabAddTask.setOnClickListener {

            addTask()
        }

        val itemTouchHelper = ItemTouchHelper(taskAdapter.getSimpleCallback())
        itemTouchHelper.attachToRecyclerView(binding.rvTodoList)
    }

    private fun addTask() {

        val customLayoutAlertDialog: View =
            layoutInflater.inflate(R.layout.custom_alert_dialog_add_task, null)
        val taskText = customLayoutAlertDialog.findViewById<EditText>(R.id.etADAddTask)

        val addTaskDialog = AlertDialog.Builder(this)
            .setTitle("Add new task")
            .setIcon(R.drawable.ic_add_task_dialog)
            .setPositiveButton("Add") { _, _ ->

                taskList
                    .add(
                        0,
                        Task(0, taskText.text.toString(), false)
                    )
                taskAdapter.notifyItemInserted(0)
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .setView(customLayoutAlertDialog)
            .create()

        addTaskDialog.window
            ?.decorView
            ?.setBackgroundResource(R.drawable.custom_alert_dialog_add_task_background)

        addTaskDialog.show()

        addTaskDialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false

        taskText.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val addTaskButton = addTaskDialog.getButton(AlertDialog.BUTTON_POSITIVE)

                addTaskButton.isEnabled = taskText.text.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

//        input.addTextChangedListener(object : TextWatcher {
//            override fun onTextChanged(
//                s: CharSequence, start: Int, before: Int,
//                count: Int
//            ) {
//            }
//
//            override fun beforeTextChanged(
//                s: CharSequence, start: Int, count: Int,
//                after: Int
//            ) {
//            }
//
//            override fun afterTextChanged(s: Editable) {
//                // Check if edittext is empty
//
//                if (TextUtils.isEmpty(s)) {
//                    // Disable ok button
//                    (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
//                        false
//                } else {
//                    // Something into edit text. Enable the button.
//                    (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
//                        true
//                }
//            }
//        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.tbItSearch -> Toast.makeText(this, "Search Button", Toast.LENGTH_SHORT).show()
            R.id.tbItDeleteAllTasks -> Toast.makeText(this, "Delete all Button", Toast.LENGTH_SHORT)
                .show()

            R.id.tbItSettings -> Toast.makeText(this, "Settings Button", Toast.LENGTH_SHORT).show()
        }

        return true
    }

}