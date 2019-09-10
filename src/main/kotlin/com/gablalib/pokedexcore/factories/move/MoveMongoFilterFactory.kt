package com.gablalib.pokedexcore.factories.move

import com.gablalib.pokedexcore.filters.MoveFilter
import com.gablalib.pokedexcore.repositories.entities.MoveEntity
import org.bson.conversions.Bson
import org.litote.kmongo.EMPTY_BSON
import org.litote.kmongo.`in` as inList

object MoveMongoFilterFactory {

    fun create(filter: MoveFilter): Bson {
        return filterNames(filter.names)
    }

    private fun filterNames(names: List<String>?): Bson {
        return if (names == null) EMPTY_BSON
            else MoveEntity::name inList names
    }
}