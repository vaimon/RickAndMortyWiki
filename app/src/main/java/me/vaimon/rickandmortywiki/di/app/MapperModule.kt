package me.vaimon.rickandmortywiki.di.app

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity
import me.vaimon.rickandmortywiki.mapper.CharacterBaseInfoMapper
import me.vaimon.rickandmortywiki.data.mapper.Mapper
import me.vaimon.rickandmortywiki.models.CharacterBaseInfo

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @Binds
    abstract fun bindCharacterBaseInfoMapper(
        mapper: CharacterBaseInfoMapper
    ) : Mapper<CharacterBaseInfo, CharacterEntity>
}