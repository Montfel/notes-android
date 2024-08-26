package com.montfel.presentation.upsertnote

sealed interface UpsertNoteUiEvent {
    data object OnSaveNote : UpsertNoteUiEvent
}