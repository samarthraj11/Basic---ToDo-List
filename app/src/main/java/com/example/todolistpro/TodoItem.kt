package com.example.todolistpro

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoItem(
    val desc: String, val ischecked: Boolean
): Parcelable
