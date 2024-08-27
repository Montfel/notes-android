package com.montfel.data.repository

import com.google.common.truth.Truth
import com.montfel.data.datasource.local.NoteDao
import com.montfel.data.model.entity.NoteEntity
import com.montfel.domain.model.Note
import com.montfel.domain.repository.NoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class NoteRepositoryImplTest {
    @MockK
    private lateinit var noteDao: NoteDao
    private lateinit var repository: NoteRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        repository = NoteRepositoryImpl(noteDao)
    }

    @Test
    fun `when calls upsertNote`() {
        runTest {
            val noteEntity = NoteEntity(
                id = 0,
                title = "definitiones",
                description = "blandit",
                dueDate = 4174
            )
            val note = Note(
                id = 0,
                title = "definitiones",
                description = "blandit",
                dueDate = 4174
            )

            coEvery { noteDao.upsertNote(noteEntity) } just runs
            repository.upsertNote(note)

            coVerify { noteDao.upsertNote(noteEntity) }
        }
    }

    @Test
    fun `when calls deleteNote`() {
        runTest {
            val noteEntity = NoteEntity(
                id = 0,
                title = "definitiones",
                description = "blandit",
                dueDate = 4174
            )
            val note = Note(
                id = 0,
                title = "definitiones",
                description = "blandit",
                dueDate = 4174
            )

            coEvery { noteDao.deleteNote(noteEntity) } just runs
            repository.deleteNote(note)

            coVerify { noteDao.deleteNote(noteEntity) }
        }
    }

    @Test
    fun `when calls getNoteById`() {
        runTest {
            val id = 0
            val noteEntity = NoteEntity(
                id = id,
                title = "definitiones",
                description = "blandit",
                dueDate = 4174
            )
            val note = Note(
                id = id,
                title = "definitiones",
                description = "blandit",
                dueDate = 4174
            )

            coEvery { noteDao.getNoteById(id) } returns noteEntity
            val result = repository.getNoteById(id)

            Truth.assertThat(result).isEqualTo(note)
            coVerify { noteDao.getNoteById(id) }
        }
    }

    @Test
    fun `when calls getAllNotes`() {
        runTest {
            val id = 0
            val notesEntity = listOf(
                NoteEntity(
                    id = id,
                    title = "definitiones",
                    description = "blandit",
                    dueDate = 4174
                )
            )
            val notes = listOf(
                Note(
                    id = id,
                    title = "definitiones",
                    description = "blandit",
                    dueDate = 4174
                )
            )

            every { noteDao.getAllNotes() } returns flowOf(notesEntity)
            val result = repository.getAllNotes().first()

            Truth.assertThat(result).isEqualTo(notes)
            verify { noteDao.getAllNotes() }
        }
    }
}