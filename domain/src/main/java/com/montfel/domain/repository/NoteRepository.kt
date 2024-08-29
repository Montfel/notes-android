package com.montfel.domain.repository

import com.montfel.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun getNoteById(noteId: Long): Note
    suspend fun upsertNote(note: Note): Long
    suspend fun deleteNote(note: Note)
}