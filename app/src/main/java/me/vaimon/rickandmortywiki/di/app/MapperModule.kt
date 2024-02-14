package me.vaimon.rickandmortywiki.di.app

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity
import me.vaimon.rickandmortywiki.mapper.SeriesCharacterMapper
import me.vaimon.rickandmortywiki.data.mapper.Mapper
import me.vaimon.rickandmortywiki.models.SeriesCharacter

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @Binds
    abstract fun bindCharacterBaseInfoMapper(
        mapper: SeriesCharacterMapper
    ) : Mapper<SeriesCharacter, CharacterEntity>
}