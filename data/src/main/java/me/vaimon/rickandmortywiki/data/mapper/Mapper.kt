package me.vaimon.rickandmortywiki.data.mapper

interface Mapper<T, E> {
    fun from(e: E): T

    fun to(t: T): E
}