package com.gablalib.pokedexcore.repositories.pokemon

import com.gablalib.pokedexcore.database.MongoDB
import com.gablalib.pokedexcore.repositories.entities.MoveEntity
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import com.gablalib.pokedexcore.repositories.entities.PokemonEntity
import com.gablalib.pokedexcore.repositories.entities.SpriteEntity
import com.gablalib.pokedexcore.repositories.move.MoveMongoRepo
import org.bson.conversions.Bson

object PokemonMongoRepo: PokemonRepo {

    private val pokemonCollection = MongoDB.getDB().getCollection<PokemonEntity>("pokemon")
    private val spritesCollection = MongoDB.getDB().getCollection<SpriteEntity>("sprites")

    override fun findAll(): List<PokemonEntity> {
        return pokemonCollection.find().filterNotNull()
    }

    override fun findByName(name: String): PokemonEntity? {
        return pokemonCollection.findOne(PokemonEntity::name eq name)
    }

    override fun findAllByFilter(mongoFilter: Bson): List<PokemonEntity> {
        return pokemonCollection.find(mongoFilter).toList()
    }

    fun findSprites(name: String): SpriteEntity? {
        return spritesCollection.findOne(SpriteEntity::name eq name)
    }
}