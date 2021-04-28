package com.gablalib.pokedexcore.repositories.eggGroup

import com.gablalib.pokedexcore.repositories.MongoRepo
import com.gablalib.pokedexcore.repositories.entities.EggGroupEntity
import org.bson.conversions.Bson
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

object EggGroupMongoRepo: MongoRepo<EggGroupEntity>() {

    override val collection = this.db.getCollection<EggGroupEntity>("egg_group")

    override fun findByName(name: String): EggGroupEntity? = collection.findOne(EggGroupEntity::name eq name)

    override fun findAll(): List<EggGroupEntity> = collection.find().filterNotNull()

    override fun findAllByFilter(mongoFilter: Bson): List<EggGroupEntity> {
        return collection.find(mongoFilter).toList()
    }
}
