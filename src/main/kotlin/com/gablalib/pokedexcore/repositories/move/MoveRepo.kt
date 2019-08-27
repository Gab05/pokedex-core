package com.gablalib.pokedexcore.repositories.move

import com.gablalib.pokedexcore.repositories.Repo
import com.gablalib.pokedexcore.repositories.entities.MoveEntity

interface MoveRepo: Repo<MoveEntity> {
    override fun findAll(): List<MoveEntity>
    override fun findByName(name: String): MoveEntity?
}