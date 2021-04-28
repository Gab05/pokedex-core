package com.gablalib.pokedexcore.repositories.entities

data class MoveEntity(override val name: String,
                      val type: String,
                      val category: String,
                      val pp: String,
                      val power: String,
                      val accuracy: String,
                      val battleDescription: String,
                      val battleEffect: String,
                      val battleEffectRate: String): Entity()