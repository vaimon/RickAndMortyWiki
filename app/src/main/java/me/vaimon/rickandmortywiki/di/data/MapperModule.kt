package me.vaimon.rickandmortywiki.di.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.vaimon.rickandmortywiki.data.mapper.CharacterDomainDataMapper
import me.vaimon.rickandmortywiki.data.models.CharacterData
import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity
import me.vaimon.rickandmortywiki.data.mapper.Mapper

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @Binds
    abstract fun bindCharacterDomainDataMapper(
        characterDomainDataMapper: CharacterDomainDataMapper
    ) : Mapper<CharacterEntity, CharacterData>
}