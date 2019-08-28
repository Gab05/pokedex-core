package mocks

import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.models.move.MoveCategory
import com.gablalib.pokedexcore.models.type.Type

class MoveMocks {
    companion object {
        fun basicMove(): Move = tackle()
        fun statusMove(): Move = agility()

        fun tackle(): Move {
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

        fun agility(): Move {
            return Move(
                name = "agility",
                type = Type.PSYCHIC,
                PP = 30,
                power = -1,
                accuracy = -1,
                battleDescription = "The user relaxes and lightens its body to move faster. This sharply raises the Speed stat.",
                battleEffect = "Raises user's Speed two stages.",
                battleEffectRate = "--",
                overworldDescription = "",
                criticalHitRatio = 4.17f,
                category = MoveCategory.OTHER,
                priority = "0",
                flags = ArrayList(),
                zMovePower = 0
            )
        }
    }
}