package com.example.up.presentation.screens.note_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.up.domain.model.Note
import com.example.up.domain.use_case.DeleteNoteUseCase
import com.example.up.domain.use_case.GetNoteByIdUseCase
import com.example.up.domain.use_case.SaveNoteUseCase
import com.example.up.presentation.navigation.Routes
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(
    savedStateHandle: SavedStateHandle,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {
    val noteId: Long? = savedStateHandle.toRoute<Routes.NoteDetails>().id

    private val _note = MutableStateFlow(Note())
    var note = _note.asStateFlow()


    fun onGeneralHealthChanged(newGeneralHealth: Float){
        _note.update { it.copy(generalHealth = newGeneralHealth) }
    }

    fun onNoteTextChanged(newText: String) {
        _note.update { it.copy(note = newText) }
    }

    fun onDrowsinessChanged(newDrowsiness: Float){
        _note.update { it.copy(drowsiness = newDrowsiness) }
    }

    fun onPressureChanged(newPressure: Float){
        _note.update { it.copy(pressure = newPressure) }
    }

    fun onWeaknessChanged(newWeakness: Float){
        _note.update { it.copy(weakness = newWeakness) }
    }

    fun saveNote(
        onComplete: () -> Unit
    ) {
        val currentNote = _note.value ?: return
        if (currentNote == Note()) return
        viewModelScope.launch {
            withContext(NonCancellable){
                saveNoteUseCase(
                    note = currentNote,
                    isNew = noteId == null
                )
            }
            onComplete()
        }
    }
    fun deleteNote(
        onComplete: () -> Unit
    ){
        val currentNote = _note.value ?: return
        viewModelScope.launch {
            if(noteId != null){
                withContext(NonCancellable){
                    deleteNoteUseCase(currentNote)
                }
            }
            onComplete()
        }
    }

    init {
        viewModelScope.launch {
            if(noteId != null){
                getNoteByIdUseCase(id = noteId)?.let { loadedNote ->
                    _note.value = loadedNote
                }
            }
        }
    }
}