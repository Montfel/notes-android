package com.montfel.presentation.notes

import com.montfel.domain.model.Note

sealed interface NotesEvent {
    data class DeleteNote(val note: Note): NotesEvent
}