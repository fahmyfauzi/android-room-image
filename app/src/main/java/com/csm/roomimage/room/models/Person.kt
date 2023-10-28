package com.csm.roomimage.room.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_table")
data class Person(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val firstName: String,
    val lastName: String,
    val profilePhoto: Bitmap
)