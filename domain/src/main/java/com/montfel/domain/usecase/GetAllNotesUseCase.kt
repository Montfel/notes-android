package com.montfel.domain.usecase

import com.montfel.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface GetAllNotesUseCase {
    operator fun invoke(): Flow<List<Note>>
}