package com.example.up.data.local.database

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.up.data.local.database.dao.NoteDao
import com.example.up.data.local.database.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}