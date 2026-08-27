package com.example.up.data.repository

import com.example.up.common.Resource
import com.example.up.data.local.database.dao.NoteDao
import com.example.up.data.local.database.entity.NoteEntity
import com.example.up.data.local.mappers.toDomain
import com.example.up.data.local.mappers.toEntity
import com.example.up.domain.model.Note
import com.example.up.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {
    override suspend fun createNote(note: Note) = dao.insert(note.toEntity())

    override suspend fun updateNote(note: Note) = dao.update(note.toEntity())

    override suspend fun deleteNote(note: Note) = dao.delete(note.toEntity())

    override suspend fun getNoteById(id: Long) = dao.getById(id = id)?.toDomain()

    override fun getAllNotesAsFlow(): Flow<Resource<List<Note>>> = dao.getAllAsFlow()
        .map<List<NoteEntity>, Resource<List<Note>>> { notes ->
            Resource.Success(notes.map { it.toDomain() })
        }
        .onStart { Resource.Loading(null) }
        .catch { Resource.Error(it.message ?: "Unknown error", null) }
}