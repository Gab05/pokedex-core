package mocks.entities

import com.gablalib.pokedexcore.repositories.entities.MoveEntity

class MoveEntityMocks {
    companion object {
        fun basicMove(): MoveEntity = tackle()
        fun statusMove(): MoveEntity = agility()

        fun tackle(): MoveEntity = MoveEntity(
            name = "tackle",
            type = "normal",
            pp = "35",
            power = "40",
            accuracy = "100",
            battleDescription = "A physical attack in which the user charges and slams into the target with its whole body.",
            battleEffect = "No effect.",
            battleEffectRate = "--",
            category = "physical"
        )

        fun agility(): MoveEntity = MoveEntity(
            name = "agility",
            type = "psychic",
            pp = "30",
            power = "-1",
            accuracy = "-1",
            battleDescription = "The user relaxes and lightens its body to move faster. This sharply raises the Speed stat.",
            battleEffect = "Raises user's Speed two stages.",
            battleEffectRate = "--",
            category = "other"
        )
    }
}