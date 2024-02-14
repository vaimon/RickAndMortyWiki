package me.vaimon.rickandmortywiki.mapper

interface Mapper<T, E> {
    fun from(e: E): T

    fun to(t: T): E
}