package com.example.firstapp.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.R


class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val viewContainer:CardView = view.findViewById(R.id.viewContainer)
    private val tvCategoryName: TextView = view.findViewById<TextView>(R.id.categoryName)
    private val divider: View = view.findViewById<View>(R.id.divider)

    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {

        val color = if (taskCategory.isSelected) {
            R.color.todo_background_card
        } else {
            R.color.todo_background_disabled
        }
        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context,color))

        itemView.setOnClickListener { onItemSelected(layoutPosition) }


        when (taskCategory) {
            TaskCategory.Business -> {
                tvCategoryName.text = "Businnes"
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_business_category
                    )
                )
            }

            TaskCategory.Other -> {
                tvCategoryName.text = "Other"
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_other_category
                    )
                )
            }

            TaskCategory.Personal -> {
                tvCategoryName.text = "Persona"
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_personal_category
                    )
                )
            }
        }

    }
}