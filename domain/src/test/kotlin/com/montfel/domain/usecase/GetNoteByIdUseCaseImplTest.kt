package com.montfel.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.montfel.domain.model.Note
import com.montfel.domain.repository.NoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetNoteByIdUseCaseImplTest {
    @MockK
    private lateinit var notesRepository: NoteRepository
    private lateinit var useCase: GetNoteByIdUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetNoteByIdUseCaseImpl(notesRepository)
    }

    @Test
    fun `when getNoteByIdUseCase is called then must call repository`() {
        runTest {
            val id = 0L
            val note = Note(
                id = id,
                title = "quo",
                description = "docendi",
                dueDate = 7002
            )

            coEvery { notesRepository.getNoteById(id) } returns note

            val result = useCase(id)

            assertThat(result).isEqualTo(note)
            coVerify { notesRepository.getNoteById(id) }
        }
    }
}