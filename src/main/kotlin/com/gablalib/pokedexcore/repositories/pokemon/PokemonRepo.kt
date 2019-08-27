package com.gablalib.pokedexcore.repositories.pokemon

import com.gablalib.pokedexcore.repositories.Repo
import com.gablalib.pokedexcore.repositories.entities.PokemonEntity

interface PokemonRepo: Repo<PokemonEntity> {
    override fun findAll(): List<PokemonEntity>
    override fun findByName(name: String): PokemonEntity?

    fun findByNationalNumber(nationalNumber: Int): List<PokemonEntity>
}