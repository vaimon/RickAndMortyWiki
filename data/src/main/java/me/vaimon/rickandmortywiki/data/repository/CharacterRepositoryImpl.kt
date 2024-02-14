package me.vaimon.rickandmortywiki.data.repository

import me.vaimon.rickandmortywiki.data.datasource.ApiDataSource
import me.vaimon.rickandmortywiki.data.models.CharacterData
import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity
import me.vaimon.rickandmortywiki.domain.repository.CharacterRepository
import me.vaimon.rickandmortywiki.data.mapper.Mapper

class CharacterRepositoryImpl(
    private val apiDataSource: ApiDataSource,
    private val characterMapper: Mapper<CharacterEntity, CharacterData>
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): List<CharacterEntity> {
        return apiDataSource.getCharacters(page).results.map{
            characterMapper.from(it)
        }
    }
}