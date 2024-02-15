package me.vaimon.rickandmortywiki.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.vaimon.rickandmortywiki.data.models.PageKey

@Dao
interface PageKeyDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertOrReplace(remoteKey: PageKey)

    @Query("SELECT * FROM remote_keys ORDER BY ID DESC LIMIT 1")
    suspend fun lastRemoteKey(): PageKey?

    @Query("SELECT * FROM remote_keys WHERE :characterId = endId ORDER BY ID DESC LIMIT 1")
    suspend fun getRemoteKeyById(characterId: Int): PageKey?

    @Query("SELECT * FROM remote_keys WHERE page = :page ORDER BY ID DESC LIMIT 1")
    suspend fun getPage(page: Int): PageKey?

    @Query("DELETE FROM remote_keys")
    suspend fun clearAll()
}