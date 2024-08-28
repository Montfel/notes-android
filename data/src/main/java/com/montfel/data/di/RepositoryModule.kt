package com.montfel.data.di

import com.montfel.data.repository.FcmRepositoryImpl
import com.montfel.data.repository.NoteRepositoryImpl
import com.montfel.domain.repository.FcmRepository
import com.montfel.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindNoteRepository(
        noteRepositoryImpl: NoteRepositoryImpl
    ): NoteRepository

    @Singleton
    @Binds
    abstract fun bindFcmRepository(
        fcmRepositoryImpl: FcmRepositoryImpl
    ): FcmRepository
}
