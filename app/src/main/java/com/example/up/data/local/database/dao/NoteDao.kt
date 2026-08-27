package com.example.up.data.local.database.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.example.up.data.local.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: NoteEntity)
    @Update
    suspend fun update(note: NoteEntity)
    @Delete
    suspend fun delete(note: NoteEntity)

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getById(id: Long): NoteEntity?
    @Query("SELECT * FROM notes ORDER BY date DESC")
    fun getAllAsFlow(): Flow<List<NoteEntity>>
}