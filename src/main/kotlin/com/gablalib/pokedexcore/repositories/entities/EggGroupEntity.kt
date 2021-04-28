package com.gablalib.pokedexcore.repositories.entities

data class EggGroupEntity(override val name: String,
                          val pokemons: List<String>): Entity()