package me.vaimon.rickandmortywiki.mapper

import me.vaimon.rickandmortywiki.data.mapper.Mapper
import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity
import me.vaimon.rickandmortywiki.models.SeriesCharacter
import javax.inject.Inject

class SeriesCharacterMapper @Inject constructor(): Mapper<SeriesCharacter, CharacterEntity> {
    override fun from(e: CharacterEntity): SeriesCharacter {
        return SeriesCharacter(
            e.id,
            e.name,
            e.status,
            e.species,
            e.type,
            e.gender,
            e.image
        )
    }

    override fun to(t: SeriesCharacter): CharacterEntity {
        return CharacterEntity(t.id,
            t.name,
            t.status,
            t.species,
            t.type,
            t.gender,
            t.imageUrl)
    }
}