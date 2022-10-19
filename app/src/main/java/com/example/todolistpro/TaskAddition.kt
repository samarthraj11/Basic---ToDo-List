package com.example.todolistpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskAddition : AppCompatActivity() {

    lateinit var tick:FloatingActionButton
    lateinit var inputTask:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_addition)

        tick = findViewById(R.id.tick)
        inputTask = findViewById(R.id.editTextTextMultiLine)
        var count:Int = 0;

        tick.setOnClickListener {
            count++
            val intent = Intent(this,MainActivity::class.java)
            val taskDesc:String = inputTask.text.toString()
            if(count >= 1)
            {
                intent.putExtra("desc",taskDesc)
                intent.putExtra("count",count)
            }
            startActivity(intent)
        }
    }
}