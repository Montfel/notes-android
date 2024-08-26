package com.montfel.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.montfel.data.util.Constants

@Entity(tableName = Constants.NOTE_TABLE)
internal data class NoteEntity(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val description: String,
    val dueDate: Long
)
