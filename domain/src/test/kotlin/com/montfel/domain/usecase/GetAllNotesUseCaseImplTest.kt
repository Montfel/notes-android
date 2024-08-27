package com.montfel.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.montfel.domain.model.Note
import com.montfel.domain.repository.NoteRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test

class GetAllNotesUseCaseImplTest {
    @MockK
    private lateinit var notesRepository: NoteRepository
    private lateinit var useCase: GetAllNotesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetAllNotesUseCaseImpl(notesRepository)
    }

    @Test
    fun `when getAllNotesUseCase is called then must call repository`() {
        val notes = flowOf(
            listOf(
                Note(
                    id = 0,
                    title = "quo",
                    description = "docendi",
                    dueDate = 7002
                )
            )
        )

        every { notesRepository.getAllNotes() } returns notes

        val result = useCase()

        assertThat(result).isEqualTo(notes)
        verify { notesRepository.getAllNotes() }
    }
}