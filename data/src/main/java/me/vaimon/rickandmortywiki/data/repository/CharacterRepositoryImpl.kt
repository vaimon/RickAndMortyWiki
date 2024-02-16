package me.vaimon.rickandmortywiki.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.vaimon.rickandmortywiki.data.datasource.ApiDataSource
import me.vaimon.rickandmortywiki.data.db.AppDatabase
import me.vaimon.rickandmortywiki.data.models.CharacterData
import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity
import me.vaimon.rickandmortywiki.domain.repository.CharacterRepository
import me.vaimon.rickandmortywiki.data.mapper.Mapper
import me.vaimon.rickandmortywiki.data.models.PageKey

class CharacterRepositoryImpl(
    private val db: AppDatabase,
    private val apiDataSource: ApiDataSource,
    private val characterMapper: Mapper<CharacterEntity, CharacterData>
) : CharacterRepository {
    override suspend fun getCharactersNextPage(lastCharacterId: Int?): List<CharacterEntity> =
        withContext(Dispatchers.IO) {
            val nextRequiredPage = getNextDbPageNumber(lastCharacterId)
                ?: throw IllegalStateException("Database must contain page for id: $lastCharacterId")
            val obtainedResult = getPageFromDatabase(nextRequiredPage) ?: run {
                val obtainedCharacterList = apiDataSource.getCharacters(nextRequiredPage).results
                savePageToDatabase(nextRequiredPage, obtainedCharacterList)
                obtainedCharacterList
            }

            obtainedResult.map {
                characterMapper.from(it)
            }
        }

    private suspend fun getNextDbPageNumber(lastCharacterId: Int?): Int? {
        return if (lastCharacterId != null) {
            db.pageKeyDao().getRemoteKeyById(lastCharacterId)?.page?.plus(1)
        } else 1
    }

    private suspend fun getPageFromDatabase(pageNumber: Int): List<CharacterData>? {
        val pageInfo = db.pageKeyDao().getPage(pageNumber) ?: return null
        return db.characterDao().getCharactersPage(pageInfo.startId, pageInfo.endId)
    }

    private suspend fun savePageToDatabase(pageNumber: Int, characterList: List<CharacterData>) {
        db.characterDao().insertAll(characterList)
        db.pageKeyDao().insertOrReplace(
            PageKey(
                page = pageNumber,
                startId = characterList.first().id,
                endId = characterList.last().id
            )
        )
    }
}