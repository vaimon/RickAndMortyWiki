package me.vaimon.rickandmortywiki.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.vaimon.rickandmortywiki.data.datasource.ApiDataSource
import me.vaimon.rickandmortywiki.data.db.AppDatabase
import me.vaimon.rickandmortywiki.data.mapper.CharacterDomainDataMapper
import me.vaimon.rickandmortywiki.data.mapper.Mapper
import me.vaimon.rickandmortywiki.data.models.CharacterData
import me.vaimon.rickandmortywiki.data.repository.CharacterRepositoryImpl
import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity
import me.vaimon.rickandmortywiki.domain.repository.CharacterRepository

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideCharacterRepository(
        db: AppDatabase,
        apiDataSource: ApiDataSource,
        characterDomainDataMapper: Mapper<CharacterEntity, CharacterData>
    ): CharacterRepository{
        return CharacterRepositoryImpl(
            db,
            apiDataSource,
            characterDomainDataMapper
        )
    }
}