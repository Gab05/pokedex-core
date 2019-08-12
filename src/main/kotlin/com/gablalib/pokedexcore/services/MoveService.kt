package com.gablalib.pokedexcore.services

import com.gablalib.pokedexcore.controllers.requests.Request
import com.gablalib.pokedexcore.factories.MoveFactory
import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.repositories.MoveMongoRepo
import com.gablalib.pokedexcore.services.responses.Response

object MoveService: Service<Move> {
    override fun processRequest(request: Request<Move>): Response<Move> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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