package com.montfel.data.di

import android.content.Context
import androidx.room.Room
import com.montfel.data.datasource.local.NotesDatabase
import com.montfel.data.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ProvideModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            Constants.NOTES_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(notesDatabase: NotesDatabase) = notesDatabase.noteDao()
}