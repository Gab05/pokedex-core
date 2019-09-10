package com.gablalib.pokedexcore.services

import com.gablalib.pokedexcore.repositories.pokemon.PokemonSpritesMongoRepo
import com.gablalib.pokedexcore.services.exceptions.PokemonNotFoundException

object SpriteService {

    fun getNormalPokemonSprite(name: String): ByteArray {
        val entity = PokemonSpritesMongoRepo.findByName(name)
            ?: throw PokemonNotFoundException(name)
        return entity.normal
    }

    fun getShinyPokemonSprite(name: String): ByteArray {
        val entity = PokemonSpritesMongoRepo.findByName(name)
            ?: throw PokemonNotFoundException(name)
        return entity.shiny
    }
}