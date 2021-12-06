package com.example.todoapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Color
import android.widget.*
import kotlinx.android.synthetic.main.todo_row.view.*

class ToDoAdapter (private val appContext: Context, private val todolist: ArrayList<Items>): RecyclerView.Adapter<ToDoAdapter.ItemViewHolder>() {
    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val todoTitle = todolist[position].titleItem
        val todoSelected = todolist[position].selected

        holder.itemView.apply {
            todoText.text = todoTitle
            if(!todoSelected) {
                todoText.setTextColor(Color.parseColor("#000000"))
            }
            else{
                todoText.setTextColor(Color.parseColor("#999999"))
            }

            selected.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {
                    if (!isChecked){
                        todoText.setTextColor(Color.parseColor("#000000"))
                        todolist[position].selected = false
                    }
                    else {
                        todoText.setTextColor(Color.parseColor("#999999"))
                        todolist[position].selected = true

                    }
                }

            })
        }
    }

    override fun getItemCount(): Int {
        return todolist.size
    }
}