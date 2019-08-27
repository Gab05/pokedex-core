package com.gablalib.pokedexcore.services

import com.gablalib.pokedexcore.factories.MoveFactory
import com.gablalib.pokedexcore.filters.MoveFilter
import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.repositories.move.MoveMongoRepo
import com.gablalib.pokedexcore.services.exceptions.MoveNotFoundException

object MoveService {
    fun getAllMoves(): List<Move> {
        val entities = MoveMongoRepo.findAll()
        return MoveFactory.createAll(entities)
    }

    fun getMoveByName(name: String): Move {
        val entity = MoveMongoRepo.findByName(name) ?: throw MoveNotFoundException(name)
        return MoveFactory.create(entity)
    }

    fun getMovesByFilter(filter: MoveFilter): List<Move> {
        return ArrayList() // TODO
    }
}