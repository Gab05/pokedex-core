package com.gablalib.pokedexcore.services

import com.gablalib.pokedexcore.factories.pokemon.EggGroupFactory
import com.gablalib.pokedexcore.models.pokemon.breeding.EggGroup
import com.gablalib.pokedexcore.repositories.eggGroup.EggGroupMongoRepo
import com.gablalib.pokedexcore.services.exceptions.EggGroupNotFoundException

object EggGroupService {

    fun getEggGroupByName(name: String): EggGroup {
        val entity = EggGroupMongoRepo.findByName(name)
            ?: throw EggGroupNotFoundException(name)
        return EggGroupFactory.create(entity)
    }
}