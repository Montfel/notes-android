package com.montfel.data.datasource.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.montfel.data.model.entity.NoteEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoInstrumentedTest {

    private lateinit var database: NotesDatabase
    private lateinit var noteDao: NoteDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(
            context,
            NotesDatabase::class.java
        ).allowMainThreadQueries().build()

        noteDao = database.noteDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertNote() {
        runTest {
            val note = NoteEntity(
                id = 0,
                title = "New note",
                description = "eius",
                dueDate = 9905
            )

            noteDao.upsertNote(note)

            val notes = noteDao.getAllNotes().first()

            assertThat(notes).contains(note)
        }
    }

    @Test
    fun updateNote() {
        runTest {
            val newNote = NoteEntity(
                id = 0,
                title = "New note",
                description = "eius",
                dueDate = 9905
            )

            val oldNote = NoteEntity(
                id = 0,
                title = "Old note",
                description = "eius",
                dueDate = 9905
            )

            noteDao.upsertNote(newNote)
            noteDao.upsertNote(oldNote)

            val notes = noteDao.getAllNotes().first()

            assertThat(notes).hasSize(1)
            assertThat(notes).contains(oldNote)
        }
    }

    @Test
    fun deleteNote() {
        runTest {
            val note = NoteEntity(
                id = 0,
                title = "New note",
                description = "eius",
                dueDate = 9905
            )

            noteDao.upsertNote(note)
            noteDao.deleteNote(note)

            val notes = noteDao.getAllNotes().first()
            assertThat(notes).isEmpty()
        }
    }

    @Test
    fun getNoteById() {
        runTest {
            val newNote = NoteEntity(
                id = 0,
                title = "New note",
                description = "eius",
                dueDate = 9905
            )

            noteDao.upsertNote(newNote)

            val note = newNote.id?.let { noteDao.getNoteById(it) }

            assertThat(note).isEqualTo(newNote)
        }
    }

    @Test
    fun getAllNotesSortedByDueDateAsc() {
        runTest {
            val note1 = NoteEntity(id = 1, title = "Note 1", description = "note", dueDate = 1)
            val note2 = NoteEntity(id = 2, title = "Note 2", description = "note", dueDate = 2)
            val note3 = NoteEntity(id = 3, title = "Note 3", description = "note", dueDate = 3)

            noteDao.upsertNote(note1)
            noteDao.upsertNote(note3)
            noteDao.upsertNote(note2)

            val notes = noteDao.getAllNotes().first()

            assertThat(notes)
                .containsExactlyElementsIn(listOf(note1, note2, note3))
                .inOrder()
        }
    }
}