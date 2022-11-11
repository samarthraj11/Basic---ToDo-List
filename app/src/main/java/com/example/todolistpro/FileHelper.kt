package com.example.todolistpro

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileHelper {
    val FILENAME = "listinfot.dat"

    fun writeData(item:ArrayList<TodoItem>, context: Context)
    {
        var fos: FileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)

        var oas = ObjectOutputStream(fos)
        oas.writeObject(item)
        oas.close()
    }
    fun readData(context: Context) : ArrayList<TodoItem>
    {
        var itemList: ArrayList<TodoItem>
        try {
            var fis: FileInputStream = context.openFileInput(FILENAME)
            var ois = ObjectInputStream(fis)
            itemList = ois.readObject() as ArrayList<TodoItem>
        }
        catch (e:FileNotFoundException)
        {
             itemList = ArrayList()
        }

        return itemList
    }
}