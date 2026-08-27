package com.example.up.domain.use_case

import com.example.up.domain.model.Note
import com.example.up.domain.repository.NoteRepository

class CreateNoteUseCase(
    val notesRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note){
        notesRepository.createNote(note = note)
    }
}