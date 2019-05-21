package com.gablalib.pokedexcore.services

import com.gablalib.pokedexcore.factories.PokemonFactory
import com.gablalib.pokedexcore.models.pokemon.Pokemon
import com.gablalib.pokedexcore.repositories.PokemonMongoRepo
import com.gablalib.pokedexcore.repositories.PokemonRepo
import com.gablalib.pokedexcore.repositories.entities.PokemonEntity

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
}