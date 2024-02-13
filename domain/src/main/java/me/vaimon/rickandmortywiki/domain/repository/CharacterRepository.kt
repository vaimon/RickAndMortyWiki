package me.vaimon.rickandmortywiki.domain.repository

import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity

interface CharacterRepository {
    suspend fun getCharacters(page: Int = 1) : List<CharacterEntity>
}