package me.vaimon.rickandmortywiki.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.vaimon.rickandmortywiki.data.datasource.ApiDataSource
import me.vaimon.rickandmortywiki.data.repository.CharacterRepositoryImpl
import me.vaimon.rickandmortywiki.domain.repository.CharacterRepository

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideCharacterRepository(
        apiDataSource: ApiDataSource
    ): CharacterRepository{
        return CharacterRepositoryImpl(apiDataSource)
    }
}