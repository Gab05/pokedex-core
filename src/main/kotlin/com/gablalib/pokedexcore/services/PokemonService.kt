package com.gablalib.pokedexcore.services

import com.gablalib.pokedexcore.factories.pokemon.PokemonFactory
import com.gablalib.pokedexcore.models.pokemon.Pokemon
import com.gablalib.pokedexcore.repositories.pokemon.PokemonMongoRepo

object PokemonService {

    fun getAllPokemons(): List<Pokemon> {
        val entities = PokemonMongoRepo.findAll()
        return PokemonFactory.createAll(entities)
    }

    fun getPokemonByName(name: String): Pokemon {
        val entity = PokemonMongoRepo.findByName(name)
        return PokemonFactory.create(entity)
    }

    fun getPokemonsByNationalNumber(nationalNumber: Int): List<Pokemon> {
        val entities = PokemonMongoRepo.findByNationalNumber(nationalNumber)
        return PokemonFactory.createAll(entities)
    }

    fun getNormalSprite(name: String): ByteArray {
        return PokemonMongoRepo.findSprites(name)!!.normal
    }

    fun getShinySprite(name: String): ByteArray {
        return PokemonMongoRepo.findSprites(name)!!.shiny
    }
}