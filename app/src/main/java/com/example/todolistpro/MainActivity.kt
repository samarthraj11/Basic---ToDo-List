package com.example.todolistpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistpro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    lateinit var add: Button
    var taskArray = ArrayList<String>()

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

        adapterWork = AdapterWork(taskArray)
        rclrView.adapter = adapterWork

        add.setOnClickListener {
            var intent = Intent(this, TaskAddition::class.java)
            startActivity(intent)
        }

//        var counter = intent.getIntExtra("count")
        var taskValue = intent.getStringExtra("desc").toString()
        var countValue = intent.getIntExtra("count",0)
        if(countValue > 0)
        {
            taskArray.add(taskValue)
            fileHelper.writeData(taskArray, applicationContext)
        }

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("taskValue",taskArray)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        taskArray = savedInstanceState.getStringArrayList("taskValue") as ArrayList<String>
    }
}