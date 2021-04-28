package com.gablalib.pokedexcore.repositories

interface Repository<E> {
    fun findAll(): Collection<E>
    fun findByName(name: String): E?
}