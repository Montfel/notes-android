package com.montfel.data.repository

import com.montfel.data.datasource.local.NoteDao
import com.montfel.data.mapper.toNote
import com.montfel.data.mapper.toNoteEntity
import com.montfel.domain.model.Note
import com.montfel.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { notesEntity ->
            notesEntity.map { noteEntity -> noteEntity.toNote() }
        }
    }

    override suspend fun getNoteById(noteId: Long): Note {
        return noteDao.getNoteById(noteId).toNote()
    }

    override suspend fun upsertNote(note: Note): Long {
        return noteDao.upsertNote(note.toNoteEntity())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toNoteEntity())
    }
}