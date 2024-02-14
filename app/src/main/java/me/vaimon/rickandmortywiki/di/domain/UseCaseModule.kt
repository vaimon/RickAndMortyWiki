package me.vaimon.rickandmortywiki.di.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.vaimon.rickandmortywiki.domain.repository.CharacterRepository
import me.vaimon.rickandmortywiki.domain.usecase.GetCharactersPageUseCase

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideGetCharactersPageUseCase(
        characterRepository: CharacterRepository
    ): GetCharactersPageUseCase {
        return GetCharactersPageUseCase(characterRepository)
    }
}