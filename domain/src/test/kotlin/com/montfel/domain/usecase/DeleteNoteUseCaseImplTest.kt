package com.montfel.domain.usecase

import com.montfel.domain.model.Note
import com.montfel.domain.repository.NoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteNoteUseCaseImplTest {
    @MockK
    private lateinit var notesRepository: NoteRepository

    private lateinit var useCase: DeleteNoteUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = DeleteNoteUseCaseImpl(notesRepository)
    }

    @Test
    fun `when deleteNoteUseCase is called then must call repository`() {
        runTest {
            val note = Note(
                id = 0,
                title = "quo",
                description = "docendi",
                dueDate = 7002
            )

            coEvery { notesRepository.deleteNote(any()) } just runs

            useCase(note)

            coVerify { notesRepository.deleteNote(note) }
        }
    }
}