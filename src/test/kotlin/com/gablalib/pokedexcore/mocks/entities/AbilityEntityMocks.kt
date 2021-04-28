package com.gablalib.pokedexcore.mocks.entities

import com.gablalib.pokedexcore.repositories.entities.AbilityEntity

class AbilityEntityMocks {
    companion object {
        fun roughSkin(): AbilityEntity = AbilityEntity(
            name = "rough_skin",
            description = "Inflicts damage to the attacker on contact.",
            battleEffect = "The opponent is hurt by 1/8th of his maxium Hit Points of recoil when using an attack," +
                    " that requires physical contact, against this Pokémon."
        )

        fun torrent(): AbilityEntity = AbilityEntity(
            name = "torrent",
            description = "Powers up Water-type moves in a pinch.",
            battleEffect = "When HP is below 1/3rd its maximum, power of Water-type moves is increased by 50%."
        )
    }
}