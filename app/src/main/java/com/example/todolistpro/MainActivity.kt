package com.example.todolistpro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistpro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CellClickListener {

    lateinit var mainBinding: ActivityMainBinding
    var taskArray = ArrayList<TodoItem>()

    lateinit var adapterWork: AdapterWork
    var fileHelper = FileHelper()

    private val startTaskAdditionActivityForResults =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val desc = result?.data?.getStringExtra("desc")
                if (desc.isNullOrEmpty().not()){
                    taskArray.add(TodoItem(desc!!,true))
                    fileHelper.writeData(taskArray, applicationContext)
                    mainBinding.rclrView.adapter?.notifyDataSetChanged()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        taskArray = fileHelper.readData(this)
        mainBinding.rclrView.layoutManager = LinearLayoutManager(this)


        adapterWork = AdapterWork(taskArray, this)
        mainBinding.rclrView.adapter = adapterWork

        mainBinding.add.setOnClickListener {
            val intent = Intent(this, TaskAddition::class.java)
            startTaskAdditionActivityForResults.launch(intent)
        }

    }

    override fun onCellClickListener(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }
}
