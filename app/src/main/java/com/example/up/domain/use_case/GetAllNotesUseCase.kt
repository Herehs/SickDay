package com.example.up.domain.use_case

import com.example.up.domain.repository.NoteRepository

class GetAllNotesUseCase(
    val notesRepository: NoteRepository
) {
    operator fun invoke() = notesRepository.getAllNotesAsFlow()
}