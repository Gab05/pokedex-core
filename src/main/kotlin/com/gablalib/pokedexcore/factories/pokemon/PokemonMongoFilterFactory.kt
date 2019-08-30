package com.gablalib.pokedexcore.factories.pokemon

import com.gablalib.pokedexcore.filters.PokemonFilter
import com.gablalib.pokedexcore.repositories.entities.PokemonEntity
import org.bson.conversions.Bson
import org.litote.kmongo.and
import org.litote.kmongo.EMPTY_BSON
import org.litote.kmongo.eq
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
        else PokemonEntity::name eq nationalNumber
    }
}