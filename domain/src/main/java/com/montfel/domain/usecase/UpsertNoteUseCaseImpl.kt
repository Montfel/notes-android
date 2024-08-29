package com.montfel.domain.usecase

import com.montfel.domain.model.Note
import com.montfel.domain.repository.NoteRepository
import javax.inject.Inject

internal class UpsertNoteUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
): UpsertNoteUseCase {
    override suspend fun invoke(note: Note): Long {
        return noteRepository.upsertNote(note = note)
    }
}