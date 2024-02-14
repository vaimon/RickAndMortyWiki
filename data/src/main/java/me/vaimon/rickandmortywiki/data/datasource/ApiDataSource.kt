package me.vaimon.rickandmortywiki.data.datasource

import me.vaimon.rickandmortywiki.data.models.CharacterListData
import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDataSource {
    companion object{
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterListData
}