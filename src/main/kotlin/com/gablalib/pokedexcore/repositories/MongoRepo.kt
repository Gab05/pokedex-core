package com.gablalib.pokedexcore.repositories

import com.mongodb.client.MongoCollection
import org.bson.conversions.Bson

interface MongoRepo<E> {

    val collection: MongoCollection<E>

    fun findAll(): Collection<E>
    fun findByName(name: String): E?
    fun findAllByFilter(mongoFilter: Bson): Collection<E>
}
