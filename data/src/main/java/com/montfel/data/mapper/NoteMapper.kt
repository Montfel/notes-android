package com.montfel.data.mapper

import com.montfel.data.model.entity.NoteEntity
import com.montfel.domain.model.Note

internal fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        description = description,
        dueDate = dueDate
    )
}

internal fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        description = description,
        dueDate = dueDate
    )
}