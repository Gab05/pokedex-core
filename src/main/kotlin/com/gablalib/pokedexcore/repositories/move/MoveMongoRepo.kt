package com.gablalib.pokedexcore.repositories.move

import com.gablalib.pokedexcore.repositories.MongoRepo
import com.gablalib.pokedexcore.repositories.entities.MoveEntity
import org.bson.conversions.Bson
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

object MoveMongoRepo: MongoRepo<MoveEntity>() {
    private const val COLLECTION_NAME = "move"

    override val collection = this.db.getCollection<MoveEntity>(COLLECTION_NAME)

    override fun findByName(name: String): MoveEntity? = collection.findOne(MoveEntity::name eq name)

    override fun findAll(): List<MoveEntity> = collection.find().filterNotNull()

    override fun findAllByFilter(mongoFilter: Bson): List<MoveEntity> = collection.find(mongoFilter).toList()
}
