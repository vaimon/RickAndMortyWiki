package me.vaimon.rickandmortywiki.data.mapper

import me.vaimon.rickandmortywiki.data.models.CharacterData
import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity
import javax.inject.Inject

class CharacterDomainDataMapper @Inject constructor(): Mapper<CharacterEntity, CharacterData> {
    override fun from(e: CharacterData): CharacterEntity {
        return CharacterEntity(
            id = e.id,
            name = e.name,
            status = e.status,
            species = e.species,
            type = e.type,
            gender = e.gender,
            image = e.image
        )
    }

    override fun to(t: CharacterEntity): CharacterData {
        return CharacterData(
            id = t.id,
            name = t.name,
            status = t.status,
            species = t.species,
            type = t.type,
            gender = t.gender,
            image = t.image
        )
    }
}