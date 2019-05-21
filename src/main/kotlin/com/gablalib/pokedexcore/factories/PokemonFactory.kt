package com.gablalib.pokedexcore.factories

import com.gablalib.pokedexcore.models.pokemon.Pokemon
import com.gablalib.pokedexcore.models.type.Type
import com.gablalib.pokedexcore.repositories.entities.PokemonEntity

object PokemonFactory {

    fun createAll(entities: List<PokemonEntity?>): List<Pokemon> {
        return entities.map { entity -> create(entity) }
    }

    fun create(entity: PokemonEntity?): Pokemon {
        return if (entity != null) {
            Pokemon(
                name = entity.name,
                baseStats = entity.stats,
                nationalNumber = entity.nationalNumber,
                typing = ArrayList(entity.type.map { rawType -> Type.valueOf(rawType.toUpperCase()) }),
                weight = entity.weight,
                captureRate = entity.captureRate,
                genderRatio = entity.genderRatio
            )
        } else {
            Pokemon("")
        }
    }
}