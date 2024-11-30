package com.example.firstapp.todoapp

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.R

class TasksViewHolder(view:View): RecyclerView.ViewHolder(view){
    private val tvTask: TextView = view.findViewById<TextView>(R.id.tvTask)
    private val check: CheckBox = view.findViewById<CheckBox>(R.id.cbTask)

    fun render(task: Task ) {
        tvTask.text = task.name
    }
}