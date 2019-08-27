package com.gablalib.pokedexcore.repositories.move

import com.gablalib.pokedexcore.database.MongoDB
import com.gablalib.pokedexcore.repositories.entities.MoveEntity
import org.bson.conversions.Bson
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

object MoveMongoRepo: MoveRepo {

    private val moveCollection = MongoDB.getDB().getCollection<MoveEntity>("move")

    override fun findByName(name: String): MoveEntity? {
        return moveCollection.findOne(MoveEntity::name eq name)
    }

    override fun findAll(): List<MoveEntity> {
        return moveCollection.find().filterNotNull()
    }

    fun findAllByFilter(mongoFilter: Bson): List<MoveEntity> {
        return moveCollection.find(mongoFilter).toList()
    }
}
