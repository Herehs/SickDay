package com.example.up.domain.use_case

import com.example.up.domain.model.Note
import com.example.up.domain.repository.NoteRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SaveNoteUseCaseTest {

    private val repository = mockk<NoteRepository>()

    private lateinit var useCase: SaveNoteUseCase

    @BeforeEach
    fun setup(){
        useCase = SaveNoteUseCase(repository)
    }

    @Test
    fun `should create new note`() = runTest {
        val note = Note(note = "abc")

        coEvery {
            repository.createNote(note = note)
        } just Runs

        useCase(note, true)

        coVerify(exactly = 1) {
            repository.createNote(note)
        }
    }

    @Test
    fun `should update note`() = runTest {

        val note = Note(id = 120, note = "abc")

        coEvery {
            repository.updateNote(note = note)
        } just Runs

        useCase(note, false)

        coVerify(exactly = 1) {
            repository.updateNote(note)
        }
    }
}