package com.gablalib.pokedexcore.repositories.entities

data class SpriteEntity(override val name: String,
                        val normal: ByteArray,
                        val shiny: ByteArray): Entity()