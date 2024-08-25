package com.montfel.presentation.notes

import com.montfel.domain.model.Note

data class HomeUiState(
    val notes: List<Note> = emptyList(),
)
