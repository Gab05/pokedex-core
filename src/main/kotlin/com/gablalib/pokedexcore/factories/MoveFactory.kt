package com.gablalib.pokedexcore.factories

import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.models.move.MoveCategory
import com.gablalib.pokedexcore.models.type.Type
import com.gablalib.pokedexcore.repositories.entities.MoveEntity

object MoveFactory {

    fun createAll(entities: List<MoveEntity?>): List<Move> {
        return entities.map { entity -> create(entity) }
    }

    fun create(entity: MoveEntity?): Move {
        return if (entity != null) {
            Move(name = entity.name,
                type = Type.valueOf(entity.type.toUpperCase()),
                category = MoveCategory.valueOf(entity.category.toUpperCase()),
                PP = entity.pp.toInt(),
                power = entity.power.toInt(),
                accuracy = entity.accuracy.toInt(),
                battleDescription = entity.battleDescription)
        } else {
            Move("")
        }
    }
}