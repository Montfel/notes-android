package com.montfel.presentation.upsertnote

sealed interface UpsertNoteEvent {
    data class GetNoteById(val noteId: Long): UpsertNoteEvent
    data object OnUpsertNote: UpsertNoteEvent
    data class OnNoteTitleChange(val title: String) : UpsertNoteEvent
    data class OnNoteDescriptionChange(val description: String) : UpsertNoteEvent
    data class OnDueDateChange(val dueTimestamp: Long) : UpsertNoteEvent
}