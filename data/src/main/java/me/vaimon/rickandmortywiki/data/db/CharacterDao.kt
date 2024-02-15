package me.vaimon.rickandmortywiki.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.vaimon.rickandmortywiki.data.models.CharacterData
import me.vaimon.rickandmortywiki.data.models.PageKey

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<CharacterData>)

    @Query("SELECT * FROM characters WHERE ID BETWEEN :startId and :endId")
    suspend fun getCharactersPage(startId: Int, endId: Int): List<CharacterData>

    @Query("DELETE FROM characters")
    suspend fun clearAll()
}