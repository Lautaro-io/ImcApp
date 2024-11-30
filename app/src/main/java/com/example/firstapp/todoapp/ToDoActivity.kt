package com.example.firstapp.todoapp

import android.app.Dialog
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ToDoActivity : AppCompatActivity() {
    private val categories = listOf<TaskCategory>(
        TaskCategory.Other,
        TaskCategory.Business,
        TaskCategory.Personal
    )
    private val tasks = mutableListOf<Task>(
        Task("Prueba", TaskCategory.Business),
        Task("Prueba2", TaskCategory.Personal),
        Task("Prueba3", TaskCategory.Other)
    )
    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CagetoriesAdapter
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var favAddTask: FloatingActionButton

    private lateinit var rvTasks: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_to_do)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initUI()
        initListeners()
    }

    private fun initComponents() {
        rvCategories = findViewById<RecyclerView>(R.id.rvCategories)
        rvTasks = findViewById<RecyclerView>(R.id.rvTasks)
        favAddTask = findViewById<FloatingActionButton>(R.id.favAddTask)
    }
    private fun initUI() {
        categoriesAdapter = CagetoriesAdapter(categories)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks)
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }

    private fun initListeners() {
        favAddTask.setOnClickListener{
            showDialog()
        }
    }
    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_tasks)
        dialog.show()
    }
}
