package com.example.todoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Color
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

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = todolist[position]

        holder.itemView.apply {
            todoText.text = item.titleItem
            selected.isChecked = item.selected

            selected.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    todoText.setTextColor(Color.parseColor("#999999"))
                    item.selected = true
                }else{
                    todoText.setTextColor(Color.parseColor("#000000"))
                    item.selected = false
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return todolist.size
    }

    fun deleteItems(){
        todolist.removeAll{ item -> item.selected }
        notifyDataSetChanged()
    }
}