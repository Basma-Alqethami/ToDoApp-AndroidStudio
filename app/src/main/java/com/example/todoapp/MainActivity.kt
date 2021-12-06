package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var myRV: RecyclerView
    lateinit var addButton: FloatingActionButton
    var toDoList = ArrayList<Items>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRV = findViewById(R.id.rvMain)
        myRV.adapter = ToDoAdapter(this, toDoList)
        myRV.layoutManager = LinearLayoutManager(this)

        addButton = findViewById(R.id.addButton)
        addButton.setOnClickListener { addNewItem() }
    }

    fun addNewItem(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("New Item")
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton("ADD") {
                dialog, which -> toDoList.add(Items(input.text.toString(), false))
        }
        builder.setNegativeButton("CANCEL") {
                dialog, which -> dialog.cancel()
        }
        builder.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {
                for (item in toDoList) {
                    if (item.selected == true) {
                        toDoList.remove(item)
                    }
                    myRV.adapter?.notifyDataSetChanged()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}