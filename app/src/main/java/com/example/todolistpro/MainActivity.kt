package com.example.todolistpro

import android.content.Intent
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistpro.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), CellClickListener {

    lateinit var mainBinding: ActivityMainBinding
    lateinit var add: FloatingActionButton
    var taskArray = ArrayList<String>()
    var checkArray = ArrayList<Boolean>()

    lateinit var rclrView: RecyclerView
    lateinit var adapterWork:AdapterWork
    var fileHelper = FileHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        add = mainBinding.add
        taskArray = fileHelper.readData(this)
        rclrView = mainBinding.rclrView
        rclrView.layoutManager = LinearLayoutManager(this)

        adapterWork = AdapterWork(taskArray,this)
        rclrView.adapter = adapterWork

        add.setOnClickListener {
            var intent = Intent(this, TaskAddition::class.java)
            startActivity(intent)
        }

        var taskValue = intent.getStringExtra("desc").toString()
        var countValue = intent.getIntExtra("count",0)
        if(countValue > 0 && taskValue.isNotEmpty())
        {
            taskArray.add(taskValue)
            fileHelper.writeData(taskArray, applicationContext)
        }
    }

    override fun onCellClickListener(data: String) {
        Toast.makeText(this,data, Toast.LENGTH_SHORT).show()
    }
}
