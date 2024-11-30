package com.example.firstapp.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater
import com.example.firstapp.R


class TasksAdapter( var tasks : List<Task>, private val onItemSelected :(Int) -> Unit) : RecyclerView.Adapter<TasksViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent, false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.render(tasks[position])
        holder.itemView.setOnClickListener { onItemSelected(position) }
    }

    override fun getItemCount() = tasks.size

}