package com.montfel.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.montfel.data.model.entity.NoteEntity

@Database(
    exportSchema = false,
    entities = [NoteEntity::class],
    version = 1
)
internal abstract class NotesDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}