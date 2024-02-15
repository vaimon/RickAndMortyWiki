package me.vaimon.rickandmortywiki.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import me.vaimon.rickandmortywiki.data.models.CharacterData
import me.vaimon.rickandmortywiki.data.models.PageKey

@Database(entities = [CharacterData::class, PageKey::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    abstract fun pageKeyDao(): PageKeyDao

    companion object {
        const val DATABASE_NAME = "characterDB"
    }

}