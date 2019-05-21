package com.gablalib.pokedexcore.repositories

import com.gablalib.pokedexcore.repositories.entities.MoveEntity

interface MoveRepo {
    fun findAll(): List<MoveEntity?>
    fun findByName(name: String): MoveEntity?
}