package com.montfel.domain.repository

import com.montfel.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun getNoteById(noteId: Int): Note
    suspend fun addNote(note: Note)
    suspend fun deleteNote(note: Note)
}