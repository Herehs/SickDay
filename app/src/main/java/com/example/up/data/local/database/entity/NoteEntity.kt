package com.example.up.data.local.database.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val date: Long,
    val generalHealth: Float,
    val note: String,
    val drowsiness: Float,
    val pressure: Float,
    val weakness: Float,
)