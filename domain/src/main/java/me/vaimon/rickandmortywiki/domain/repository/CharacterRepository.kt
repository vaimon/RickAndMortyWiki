package me.vaimon.rickandmortywiki.domain.repository

import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity

interface CharacterRepository {
    suspend fun getCharactersNextPage(lastCharacterId: Int? = null) : List<CharacterEntity>
}