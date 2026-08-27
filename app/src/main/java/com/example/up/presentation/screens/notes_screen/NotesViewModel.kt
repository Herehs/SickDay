package com.example.up.presentation.screens.notes_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.up.common.Resource
import com.example.up.domain.model.Note
import com.example.up.domain.use_case.GetAllNotesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class NotesViewModel(
    getAllNotesUseCase: GetAllNotesUseCase
) : ViewModel() {

    val notesState: StateFlow<Resource<List<Note>>> = getAllNotesUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Resource.Loading(null)
    )
}