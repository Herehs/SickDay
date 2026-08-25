package com.example.up.domain.repository

import com.example.up.common.Resource
import com.example.up.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun createNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    fun getAllNotesAsFlow(): Flow<Resource<List<Note>>>
}