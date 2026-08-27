package com.example.up.domain.use_case

import com.example.up.domain.model.Note
import com.example.up.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note){
        noteRepository.deleteNote(note = note)
    }
}