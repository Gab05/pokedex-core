package com.gablalib.pokedexcore.repositories

import com.gablalib.pokedexcore.database.MongoDB
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import com.gablalib.pokedexcore.repositories.entities.PokemonEntity
import com.gablalib.pokedexcore.repositories.entities.SpriteEntity

object PokemonMongoRepo: PokemonRepo {

    private val pokemonCollection = MongoDB.getDB().getCollection<PokemonEntity>("pokemon")
    private val spritesCollection = MongoDB.getDB().getCollection<SpriteEntity>("sprites")

    override fun findAll(): List<PokemonEntity> {
        return pokemonCollection.find().filterNotNull()
    }

    override fun findByName(name: String): PokemonEntity? {
        return pokemonCollection.findOne(PokemonEntity::name eq name)
    }

    override fun findByNationalNumber(nationalNumber: Int): List<PokemonEntity> {
        return pokemonCollection.find(PokemonEntity::nationalNumber eq nationalNumber).filterNotNull()
    }

    fun findSprites(name: String): SpriteEntity? {
        return spritesCollection.findOne(SpriteEntity::name eq name)
    }
}