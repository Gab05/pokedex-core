package com.gablalib.pokedexcore.services

import com.gablalib.pokedexcore.factories.MoveFactory
import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.repositories.MoveMongoRepo

object MoveService {

    fun getAllMoves(): List<Move> {
        val entities = MoveMongoRepo.findAll()
        return MoveFactory.createAll(entities)
    }

    fun getMoveByName(name: String): Move {
        val entity = MoveMongoRepo.findByName(name)
        return MoveFactory.create(entity)
    }

    fun getMovesByNameList(names: List<String>): List<Move> {
        val entities = MoveMongoRepo.findAllByNameList(names)
        return MoveFactory.createAll(entities)
    }
}