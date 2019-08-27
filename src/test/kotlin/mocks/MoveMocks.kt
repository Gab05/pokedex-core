package mocks

import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.models.move.MoveCategory
import com.gablalib.pokedexcore.models.type.Type

class MoveMocks {
    companion object {
        fun basicMove(): Move {
            return Move(
                name = "tackle",
                type = Type.NORMAL,
                PP = 35,
                power = 40,
                accuracy = 100,
                battleDescription = "A physical attack in which the user charges and slams into the target with its whole body.",
                battleEffect = "No effect.",
                battleEffectRate = "--",
                overworldDescription = "",
                criticalHitRatio = 4.17f,
                category = MoveCategory.PHYSICAL,
                priority = "0",
                flags = ArrayList(),
                zMovePower = 0
            )
        }
    }
}