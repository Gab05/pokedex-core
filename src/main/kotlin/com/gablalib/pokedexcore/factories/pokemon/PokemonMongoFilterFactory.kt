package com.gablalib.pokedexcore.factories.pokemon

import com.gablalib.pokedexcore.filters.PokemonFilter
import com.gablalib.pokedexcore.models.move.LevelUpMove
import com.gablalib.pokedexcore.models.move.TmMove
import com.gablalib.pokedexcore.models.pokemon.ability.Abilities
import com.gablalib.pokedexcore.repositories.entities.PokemonEntity
import org.bson.conversions.Bson
import org.litote.kmongo.*
import org.litote.kmongo.`in` as inList

object PokemonMongoFilterFactory {

    fun create(filter: PokemonFilter): Bson {
        return and(
            filterNames(filter.names),
            filterNationalNumber(filter.nationalNumber),
            filterType(filter.type),
            filterMoves(filter.move),
            filterAbility(filter.ability)
        )
    }

    private fun filterNames(names: List<String>?): Bson {
        return if (names == null) EMPTY_BSON
        else PokemonEntity::name inList names
    }

    private fun filterNationalNumber(nationalNumber: Int?): Bson {
        return if (nationalNumber == null) EMPTY_BSON
        else PokemonEntity::nationalNumber eq nationalNumber
    }

    private fun filterType(type: String?): Bson {
        return if (type == null) EMPTY_BSON
        else PokemonEntity::type contains type
    }

    private fun filterMoves(move: String?): Bson {
        return if (move == null) EMPTY_BSON
        else or(
            PokemonEntity::eggMoves contains move,
            PokemonEntity::tmMoves / TmMove::move eq move,
            PokemonEntity::levelUpMoves / LevelUpMove::move eq move
        )
    }

    private fun filterAbility(ability: String?): Bson {
        return if (ability == null) EMPTY_BSON
        else or(
            PokemonEntity::abilities / Abilities::first eq ability,
            PokemonEntity::abilities / Abilities::second eq ability,
            PokemonEntity::abilities / Abilities::hidden eq ability
        )
    }
}