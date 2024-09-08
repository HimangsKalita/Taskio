package com.himangskalita.taskio

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.himangskalita.taskio.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupEdgeToEdgeInsets()
        initialization()


        binding.fabAddTask.setOnClickListener{ view ->

            Snackbar.make(view, "Task Added", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.tbItSearch -> Toast.makeText(this, "Search Button", Toast.LENGTH_SHORT).show()
            R.id.tbItDeleteAllTasks -> Toast.makeText(this, "Delete all Button", Toast.LENGTH_SHORT).show()
            R.id.tbItSettings -> Toast.makeText(this, "Settings Button", Toast.LENGTH_SHORT).show()
        }

        return true
    }

    private fun setupEdgeToEdgeInsets() {

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initialization() {

        setSupportActionBar(binding.toolbar)
        window.statusBarColor = ContextCompat.getColor(this, R.color.blueDark)
    }
}