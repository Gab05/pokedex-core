package mocks

import com.gablalib.pokedexcore.repositories.entities.MoveEntity

class MoveEntityMocks {
    companion object {
        fun basicMove(): MoveEntity {
            return MoveEntity(
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
        }
    }
}