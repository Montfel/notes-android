package com.montfel.presentation.notes

import com.montfel.domain.model.Note

sealed interface HomeEvent {
    data class DeleteNote(val note: Note): HomeEvent
}