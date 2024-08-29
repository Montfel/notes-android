package com.montfel.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.montfel.data.util.Constants

@Entity(tableName = Constants.NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey
    val id: Long? = null,
    val title: String,
    val description: String,
    val dueDate: Long
)
