package me.vaimon.rickandmortywiki.mapper

import me.vaimon.rickandmortywiki.data.mapper.Mapper
import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity
import me.vaimon.rickandmortywiki.models.CharacterBaseInfo
import javax.inject.Inject

class CharacterBaseInfoMapper @Inject constructor(): Mapper<CharacterBaseInfo, CharacterEntity> {
    override fun from(e: CharacterEntity): CharacterBaseInfo {
        return CharacterBaseInfo(e.id, e.name)
    }

    override fun to(t: CharacterBaseInfo): CharacterEntity {
        return CharacterEntity(t.id, t.name, "", "", "", "", "")
    }
}