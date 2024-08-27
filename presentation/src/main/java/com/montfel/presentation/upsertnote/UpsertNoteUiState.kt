package com.montfel.presentation.upsertnote

data class UpsertNoteUiState(
    val title: String = "",
    val titleSuccessful: Boolean = true,
    val description: String = "",
    val dueDate: String = "",
    val dueDateSuccessful: Boolean = true,
)
