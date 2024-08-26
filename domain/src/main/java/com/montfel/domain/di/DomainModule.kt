package com.montfel.domain.di

import com.montfel.domain.usecase.AddNoteUseCase
import com.montfel.domain.usecase.AddNoteUseCaseImpl
import com.montfel.domain.usecase.DeleteNoteUseCase
import com.montfel.domain.usecase.DeleteNoteUseCaseImpl
import com.montfel.domain.usecase.GetAllNotesUseCase
import com.montfel.domain.usecase.GetAllNotesUseCaseImpl
import com.montfel.domain.usecase.GetNoteByIdUseCase
import com.montfel.domain.usecase.GetNoteByIdUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DomainModule {
    @Binds
    abstract fun bindAddNoteUseCase(
        addNoteUseCaseImpl: AddNoteUseCaseImpl
    ): AddNoteUseCase

    @Binds
    abstract fun bindDeleteNoteUseCase(
        deleteNoteUseCaseImpl: DeleteNoteUseCaseImpl
    ): DeleteNoteUseCase

    @Binds
    abstract fun bindGetAllNotesUseCase(
        getAllNotesUseCaseImpl: GetAllNotesUseCaseImpl
    ): GetAllNotesUseCase

    @Binds
    abstract fun bindGetNoteByIdUseCase(
        getNoteByIdUseCaseImpl: GetNoteByIdUseCaseImpl
    ): GetNoteByIdUseCase
}