package com.montfel.domain.usecase

import com.montfel.domain.model.Note

interface UpsertNoteUseCase {
    suspend operator fun invoke(note: Note): Long
}