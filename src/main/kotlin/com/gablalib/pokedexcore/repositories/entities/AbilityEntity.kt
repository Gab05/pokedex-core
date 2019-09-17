package com.gablalib.pokedexcore.repositories.entities

data class AbilityEntity(override val name: String,
                         val description: String,
                         val battleEffect: String): Entity()