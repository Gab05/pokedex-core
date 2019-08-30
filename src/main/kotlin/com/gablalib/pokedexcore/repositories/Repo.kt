package com.gablalib.pokedexcore.repositories

interface Repo<E> {
    fun findAll(): Collection<E>
    fun findByName(name: String): E?
}
