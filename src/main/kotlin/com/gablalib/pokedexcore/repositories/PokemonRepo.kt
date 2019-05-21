package com.gablalib.pokedexcore.repositories

import com.gablalib.pokedexcore.repositories.entities.PokemonEntity

interface PokemonRepo {
    fun findAll(): List<PokemonEntity>
    fun findByName(name: String): PokemonEntity?
    fun findByNationalNumber(nationalNumber: Int): List<PokemonEntity>
}