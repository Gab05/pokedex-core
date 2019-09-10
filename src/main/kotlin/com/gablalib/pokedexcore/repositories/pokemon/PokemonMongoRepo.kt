package com.gablalib.pokedexcore.repositories.pokemon

import com.gablalib.pokedexcore.database.MongoDB
import com.gablalib.pokedexcore.repositories.MongoRepo
import com.gablalib.pokedexcore.repositories.entities.PokemonEntity
import org.bson.conversions.Bson
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

object PokemonMongoRepo: MongoRepo<PokemonEntity> {

    override val collection = MongoDB.getDB().getCollection<PokemonEntity>("pokemon")

    override fun findAll(): List<PokemonEntity> = collection.find().filterNotNull()

    override fun findByName(name: String): PokemonEntity? = collection.findOne(PokemonEntity::name eq name)

    override fun findAllByFilter(mongoFilter: Bson): List<PokemonEntity> = collection.find(mongoFilter).toList()
}