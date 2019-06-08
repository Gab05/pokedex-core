package com.gablalib.pokedexcore.services

import com.gablalib.pokedexcore.factories.PokemonFactory
import com.gablalib.pokedexcore.models.pokemon.Pokemon
import com.gablalib.pokedexcore.repositories.PokemonMongoRepo
import org.springframework.core.io.ClassPathResource

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
        val resource = ClassPathResource("sprites/normal/$name.gif")
        return resource.inputStream.readBytes()
    }

    fun getShinySprite(name: String): ByteArray {
        val resource = ClassPathResource("/sprites/shiny/$name.gif")
        return resource.inputStream.readBytes()
    }
}