package me.vaimon.rickandmortywiki.data.repository

import me.vaimon.rickandmortywiki.data.datasource.ApiDataSource
import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity
import me.vaimon.rickandmortywiki.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    val apiDataSource: ApiDataSource
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): List<CharacterEntity> {
        return apiDataSource.getCharacters(page)
    }
}