package com.gablalib.pokedexcore.repositories

import com.gablalib.pokedexcore.database.MongoDB
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import com.gablalib.pokedexcore.repositories.entities.PokemonEntity

object PokemonMongoRepo: PokemonRepo {

    val pokemonCollection = MongoDB.getDB().getCollection<PokemonEntity>("pokemon")

    override fun findAll(): List<PokemonEntity> {
        return pokemonCollection.find().filterNotNull()
    }

    override fun findByName(name: String): PokemonEntity? {
        return pokemonCollection.findOne(PokemonEntity::name eq name)
    }

    override fun findByNationalNumber(nationalNumber: Int): List<PokemonEntity> {
        return pokemonCollection.find(PokemonEntity::nationalNumber eq nationalNumber).filterNotNull()
    }
}