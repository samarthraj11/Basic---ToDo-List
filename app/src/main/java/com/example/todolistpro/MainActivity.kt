package com.example.todolistpro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolistpro.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), CellClickListener {

    lateinit var mainBinding: ActivityMainBinding
    var taskArray = ArrayList<String>()
    lateinit var database: ContactDatabase

    lateinit var adapterWork: AdapterWork

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.rclrView.layoutManager = LinearLayoutManager(this)
        adapterWork = AdapterWork(taskArray, this)
        mainBinding.rclrView.adapter = adapterWork

        mainBinding.add.setOnClickListener {
            val intent = Intent(this, TaskAddition::class.java)
        }

        database = Room.databaseBuilder(applicationContext,
                    ContactDatabase::class.java,
        "contactDB").build()

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0,"SAM", "99"))
        }

    }

    override fun onCellClickListener(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }
}
