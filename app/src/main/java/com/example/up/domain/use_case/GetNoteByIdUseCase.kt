package com.example.up.domain.use_case

import com.example.up.domain.repository.NoteRepository

class GetNoteByIdUseCase(
    val notesRepository: NoteRepository
) {
    suspend operator fun invoke(id: Long) = notesRepository.getNoteById(id = id)
}