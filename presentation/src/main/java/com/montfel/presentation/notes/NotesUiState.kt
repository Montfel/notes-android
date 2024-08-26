package com.montfel.presentation.notes

import com.montfel.domain.model.Note

data class NotesUiState(
    val notes: List<Note> = emptyList(),
)
