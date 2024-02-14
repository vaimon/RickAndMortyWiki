package me.vaimon.rickandmortywiki.domain.usecase

import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity
import me.vaimon.rickandmortywiki.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersPageUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(page: Int = 1): List<CharacterEntity>{
        return characterRepository.getCharacters(page).map{
            if(it.type.isBlank()) it.copy(type = "Unknown") else it
        }
    }
}