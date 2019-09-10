package com.gablalib.pokedexcore.services

import com.gablalib.pokedexcore.factories.move.AbilityFactory
import com.gablalib.pokedexcore.factories.move.AbilityMongoFilterFactory
import com.gablalib.pokedexcore.filters.AbilityFilter
import com.gablalib.pokedexcore.models.pokemon.ability.Ability
import com.gablalib.pokedexcore.repositories.ability.AbilityMongoRepo
import com.gablalib.pokedexcore.services.exceptions.AbilityNotFoundException

object AbilityService {
    fun getAllAbilities(): List<Ability> {
        val entities = AbilityMongoRepo.findAll()
        return AbilityFactory.createAll(entities)
    }

    fun getAbilityByName(name: String): Ability {
        val entity = AbilityMongoRepo.findByName(name)
            ?: throw AbilityNotFoundException(name)
        return AbilityFactory.create(entity)
    }

    fun getAbilitiesByFilter(filter: AbilityFilter): List<Ability> {
        val mongoFilter = AbilityMongoFilterFactory.create(filter)
        val entities = AbilityMongoRepo.findAllByFilter(mongoFilter)
        return AbilityFactory.createAll(entities)
    }
}