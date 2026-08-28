package com.example.up.domain.use_case

import com.example.up.domain.model.Note
import com.example.up.domain.repository.NoteRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetNoteByIdUseCaseTest {
2
    private val repository = mockk<NoteRepository>()

    private lateinit var useCase: GetNoteByIdUseCase

    @Before
    fun setup(){
        useCase = GetNoteByIdUseCase(repository)
    }

    @Test
    fun `should return note by id`() = runTest {
        val note = Note(
            id = 120
        )

        coEvery {
            repository.getNoteById(120)
        } returns note

        val result = useCase(120)

        assertEquals(
            note,
            result
        )

        coVerify {
            repository.getNoteById(120)
        }
    }
}