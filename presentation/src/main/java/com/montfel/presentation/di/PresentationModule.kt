package com.montfel.presentation.di

import android.content.Context
import com.montfel.presentation.notification.NotesAlarmManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {
    @Singleton
    @Provides
    fun provideNotesAlarmManager(@ApplicationContext context: Context): NotesAlarmManager {
        return NotesAlarmManager(context)
    }
}