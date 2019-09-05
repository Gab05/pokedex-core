package com.gablalib.pokedexcore.factories.move

import com.gablalib.pokedexcore.filters.AbilityFilter
import com.gablalib.pokedexcore.repositories.entities.AbilityEntity
import org.bson.conversions.Bson
import org.litote.kmongo.EMPTY_BSON
import org.litote.kmongo.`in` as inList

object AbilityMongoFilterFactory {

    fun create(filter: AbilityFilter): Bson = filterNames(filter.names)

    private fun filterNames(names: List<String>?): Bson {
        return if (names == null) EMPTY_BSON
            else AbilityEntity::name inList names
    }
}