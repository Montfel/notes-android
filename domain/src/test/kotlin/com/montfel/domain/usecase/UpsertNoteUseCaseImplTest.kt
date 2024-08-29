package com.montfel.domain.usecase

import com.montfel.domain.model.Note
import com.montfel.domain.repository.NoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UpsertNoteUseCaseImplTest {
    @MockK
    private lateinit var notesRepository: NoteRepository

    private lateinit var useCase: UpsertNoteUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = UpsertNoteUseCaseImpl(notesRepository)
    }

    @Test
    fun `when upsertNoteUseCase is called then must call repository`() {
        runTest {
            val id = 0L
            val note = Note(
                id = id,
                title = "quo",
                description = "docendi",
                dueDate = 7002
            )

            coEvery { notesRepository.upsertNote(any()) } returns id

            useCase(note)

            coVerify { notesRepository.upsertNote(note) }
        }
    }
}