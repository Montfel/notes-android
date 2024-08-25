package com.montfel.data.repository

import com.montfel.data.datasource.local.NotesDatabase
import com.montfel.data.mapper.toNote
import com.montfel.data.mapper.toNoteEntity
import com.montfel.domain.model.Note
import com.montfel.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val database: NotesDatabase
) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return database.noteDao().getAllNotes().map { notesEntity ->
            notesEntity.map { noteEntity -> noteEntity.toNote() }
        }
    }

    override suspend fun getNoteById(noteId: Int): Note {
        return database.noteDao().getNoteById(noteId).toNote()
    }

    override suspend fun addNote(note: Note) {
        database.noteDao().addNote(note.toNoteEntity())
    }

    override suspend fun deleteNote(note: Note) {
        database.noteDao().deleteNote(note.toNoteEntity())
    }
}