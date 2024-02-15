package me.vaimon.rickandmortywiki.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class PageKey(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val page: Int,
    val startId: Int,
    val endId: Int,
)