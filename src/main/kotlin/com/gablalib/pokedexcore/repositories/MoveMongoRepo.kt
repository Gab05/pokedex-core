package com.gablalib.pokedexcore.repositories

import com.gablalib.pokedexcore.database.MongoDB
import com.gablalib.pokedexcore.repositories.entities.MoveEntity
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

object MoveMongoRepo: MoveRepo {

    val moveCollection = MongoDB.getDB().getCollection<MoveEntity>("move")

    override fun findAll(): List<MoveEntity?> {
        return moveCollection.find().filterNotNull()
    }

    override fun findByName(name: String): MoveEntity? {
        return moveCollection.findOne(MoveEntity::name eq name)
    }

}