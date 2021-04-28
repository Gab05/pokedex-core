package com.gablalib.pokedexcore.repositories

import com.gablalib.pokedexcore.database.MongoDB
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.conversions.Bson

abstract class MongoRepo<E>: Repository<E> {

    protected val db: MongoDatabase = MongoDB.getDB()
    abstract val collection: MongoCollection<E>

    abstract override fun findAll(): Collection<E>
    abstract override fun findByName(name: String): E?
    abstract fun findAllByFilter(mongoFilter: Bson): Collection<E>
}
