package com.montfel.domain.usecase

import com.montfel.domain.model.Note
import com.montfel.domain.repository.NoteRepository
import javax.inject.Inject

internal class AddNoteUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
): AddNoteUseCase {
    override suspend fun invoke(note: Note) {
        noteRepository.addNote(note = note)
    }
}