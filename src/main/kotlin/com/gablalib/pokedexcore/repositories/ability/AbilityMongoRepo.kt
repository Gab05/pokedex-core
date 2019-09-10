package com.gablalib.pokedexcore.repositories.ability

import com.gablalib.pokedexcore.database.MongoDB
import com.gablalib.pokedexcore.repositories.MongoRepo
import com.gablalib.pokedexcore.repositories.entities.AbilityEntity
import org.bson.conversions.Bson
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

object AbilityMongoRepo: MongoRepo<AbilityEntity> {
    override val collection = MongoDB.getDB().getCollection<AbilityEntity>("ability")

    override fun findAll(): List<AbilityEntity> = collection.find().filterNotNull()

    override fun findByName(name: String): AbilityEntity? = collection.findOne(AbilityEntity::name eq name)

    override fun findAllByFilter(mongoFilter: Bson): List<AbilityEntity> = collection.find(mongoFilter).toList()
}