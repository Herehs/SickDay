package com.example.up.domain.use_case

import com.example.up.domain.model.Note
import com.example.up.domain.repository.NoteRepository
import java.time.LocalDate
import java.time.ZoneId

class SaveNoteUseCase(
    private val notesRepository: NoteRepository,
    private val timeProvider: () -> Long = {
        LocalDate.now()
            .atStartOfDay(ZoneId.systemDefault())
            .toEpochSecond()
    }

){
    suspend operator fun invoke(note: Note, isNew: Boolean){
        if(isNew){
            notesRepository.createNote(
                note = note.copy(
                    date = timeProvider()
                )
            )
        } else {
            notesRepository.updateNote(note = note)
        }
    }
}