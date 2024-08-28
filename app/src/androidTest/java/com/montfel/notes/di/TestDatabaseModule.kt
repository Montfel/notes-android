package com.montfel.notes.di

import android.content.Context
import androidx.room.Room
import com.montfel.data.datasource.local.NotesDatabase
import com.montfel.data.di.DatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
internal object TestDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NotesDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            NotesDatabase::class.java,
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(notesDatabase: NotesDatabase) = notesDatabase.noteDao()
}