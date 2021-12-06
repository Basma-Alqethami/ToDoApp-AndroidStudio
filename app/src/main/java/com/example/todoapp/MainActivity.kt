package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var myRV: RecyclerView
    lateinit var addButton: FloatingActionButton
    private lateinit var rvAdapter: ToDoAdapter
    private lateinit var clMain: ConstraintLayout

    var toDoList = ArrayList<Items>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clMain = findViewById(R.id.clMain)

        myRV = findViewById(R.id.rvMain)
        rvAdapter = ToDoAdapter(this, toDoList)
        myRV.adapter = rvAdapter
        myRV.layoutManager = LinearLayoutManager(this)

        addButton = findViewById(R.id.addButton)
        addButton.setOnClickListener { addNewItem() }
    }

    fun addNewItem() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("New Item")
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
            builder.setPositiveButton("ADD") { dialog, which ->
                if (input.text.isNotEmpty()) {
                    toDoList.add(Items(input.text.toString(), false)) }
            }
            builder.setNegativeButton("CANCEL") { dialog, which ->
                dialog.cancel()
            }
        builder.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemsDeleted = 0
        for(i in toDoList){
            if(i.selected){itemsDeleted++}
        }

        if(itemsDeleted > 0){
            Snackbar.make(clMain, "$itemsDeleted items deleted", Snackbar.LENGTH_LONG).show()

        }else{
            Snackbar.make(clMain, "No items selected", Snackbar.LENGTH_LONG).show()
        }
        rvAdapter.deleteItems()
        return super.onOptionsItemSelected(item)
    }
}