package com.example.up.data.local.database.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: ULong,
    val date: Long,
    var generalHealth: Int,
    var note: String,
    var drowsiness: Int,
    var pressure: Int,
    var weakness: Int
)