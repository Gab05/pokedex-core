package com.gablalib.pokedexcore.factories.pokemon

import com.gablalib.pokedexcore.filters.PokemonFilter
import com.gablalib.pokedexcore.repositories.entities.PokemonEntity
import org.bson.conversions.Bson
import org.litote.kmongo.*
import org.litote.kmongo.`in` as inList

object PokemonMongoFilterFactory {

    fun create(filter: PokemonFilter): Bson {
        return and(
            filterNames(filter.names),
            filterNationalNumber(filter.nationalNumber)
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

    private fun filterTypes(types: List<String>?): Bson {
        var mongoFilter = EMPTY_BSON
        return if (types == null) mongoFilter
        else {
            for (type in types) mongoFilter = and(mongoFilter, PokemonEntity::type contains type)
            mongoFilter
        }
    }

    private fun filterMoves(moves: List<String>?): Bson {
        return if (moves == null) EMPTY_BSON
        else or(
            PokemonEntity::tmMoves inList moves,
            PokemonEntity::eggMoves inList moves,
            PokemonEntity::levelUpMoves inList moves
        )
    }

    private fun filterAbilities(abilities: List<String>?): Bson {
        return if (abilities == null) EMPTY_BSON
        else or(
            PokemonEntity::abilities inList abilities
        )
    }
}