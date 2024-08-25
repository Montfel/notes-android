package com.montfel.presentation.addeditnote

sealed interface AddEditNoteEvent {
    data class GetNoteById(val noteId: Int): AddEditNoteEvent
    data object OnSaveNote: AddEditNoteEvent
    data class OnNoteTitleChange(val title: String) : AddEditNoteEvent
    data class OnNoteDescriptionChange(val description: String) : AddEditNoteEvent
    data class OnDueDateChange(val dueTimestamp: Long) : AddEditNoteEvent
}