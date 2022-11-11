package com.example.todolistpro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterWork(var textValue: ArrayList<String>, val cellClickListener: CellClickListener):RecyclerView.Adapter<AdapterWork.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textString:TextView = itemView.findViewById(R.id.task)
        val checkValue: CheckBox = itemView.findViewById(R.id.cb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.task_layout,parent,false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.textString.text = textValue.get(position)
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(textValue.get(position))
        }
    }

    override fun getItemCount(): Int {
        return textValue.size
    }
}