package com.gablalib.pokedexcore.factories.move

import com.gablalib.pokedexcore.models.pokemon.ability.Ability
import com.gablalib.pokedexcore.repositories.entities.AbilityEntity

object AbilityFactory {

    fun createAll(entities: List<AbilityEntity>): List<Ability> = entities.map { entity -> create(entity) }

    fun create(entity: AbilityEntity): Ability = Ability(
        name = entity.name,
        description = entity.description,
        battleEffect = entity.battleEffect
    )
}