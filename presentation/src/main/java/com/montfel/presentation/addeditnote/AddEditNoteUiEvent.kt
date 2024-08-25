package com.montfel.presentation.addeditnote

sealed interface AddEditNoteUiEvent {
    data object OnSaveNote : AddEditNoteUiEvent
}