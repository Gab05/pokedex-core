package com.gablalib.pokedexcore.services.requestHandlers

import com.gablalib.pokedexcore.controllers.requests.MoveRequest
import com.gablalib.pokedexcore.controllers.requests.MovesRequest
import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.services.MoveService

object MoveRequestHandler {

    fun handleMoveRequest(request: MoveRequest): Move {
        return MoveService.getMoveByName(request.name)
    }

    fun handleMovesRequest(request: MovesRequest?): List<Move> {
        return if (request?.filter == null)
            MoveService.getAllMoves()
        else
            MoveService.getMovesByFilter(request.filter)
    }
}