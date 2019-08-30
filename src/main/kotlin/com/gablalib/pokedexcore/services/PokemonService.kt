package com.gablalib.pokedexcore.services

import com.gablalib.pokedexcore.factories.pokemon.PokemonFactory
import com.gablalib.pokedexcore.factories.pokemon.PokemonMongoFilterFactory
import com.gablalib.pokedexcore.filters.PokemonFilter
import com.gablalib.pokedexcore.models.pokemon.Pokemon
import com.gablalib.pokedexcore.repositories.pokemon.PokemonMongoRepo
import com.gablalib.pokedexcore.services.exceptions.PokemonNotFoundException

object PokemonService {

    fun getAllPokemons(): List<Pokemon> {
        val entities = PokemonMongoRepo.findAll()
        return PokemonFactory.createAll(entities)
    }

    fun getPokemonByName(name: String): Pokemon {
        val entity = PokemonMongoRepo.findByName(name)
            ?: throw PokemonNotFoundException(name)
        return PokemonFactory.create(entity)
    }

    fun getPokemonsByFilter(filter: PokemonFilter): List<Pokemon> {
        val mongoFilter = PokemonMongoFilterFactory.create(filter)
        val entities = PokemonMongoRepo.findAllByFilter(mongoFilter)
        return PokemonFactory.createAll(entities)
    }

    fun getNormalSprite(name: String): ByteArray {
        return PokemonMongoRepo.findSprites(name)!!.normal
    }

    fun getShinySprite(name: String): ByteArray {
        return PokemonMongoRepo.findSprites(name)!!.shiny
    }
}