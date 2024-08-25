package com.montfel.presentation.addeditnote

sealed interface AddEditNoteEvent {
    data class GetEditNoteById(val noteId: Int): AddEditNoteEvent
    data object OnAddEditNote: AddEditNoteEvent
    data class OnEditNoteTitleChange(val title: String) : AddEditNoteEvent
    data class OnEditNoteDescriptionChange(val description: String) : AddEditNoteEvent
    data class OnDueDateChange(val dueDate: Long) : AddEditNoteEvent
}