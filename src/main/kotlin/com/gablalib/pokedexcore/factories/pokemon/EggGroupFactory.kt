package com.gablalib.pokedexcore.factories.pokemon

import com.gablalib.pokedexcore.models.pokemon.breeding.EggGroup
import com.gablalib.pokedexcore.repositories.entities.EggGroupEntity

object EggGroupFactory {

    fun createAll(entities: List<EggGroupEntity>): List<EggGroup> {
        return entities.map { entity -> create(entity) }
    }

    fun create(entity: EggGroupEntity): EggGroup = EggGroup(
        name = entity.name,
        pokemons = ArrayList(entity.pokemons.map { pokemon -> pokemon })
    )
}