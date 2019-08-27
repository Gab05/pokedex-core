package com.gablalib.pokedexcore.services.requestHandlers

import com.gablalib.pokedexcore.controllers.requests.MoveRequest
import com.gablalib.pokedexcore.controllers.requests.MovesRequest
import com.gablalib.pokedexcore.services.MoveService
import com.gablalib.pokedexcore.services.responses.MoveResponse
import com.gablalib.pokedexcore.services.responses.MovesResponse

object MoveRequestHandler {

    fun handleMoveRequest(request: MoveRequest): MoveResponse {
        return MoveResponse(MoveService.getMoveByName(request.name))
    }

    fun handleMovesRequest(request: MovesRequest?): MovesResponse {
        return if (request?.filter == null)
            MovesResponse(MoveService.getAllMoves())
        else
            MovesResponse(MoveService.getMovesByFilter(request.filter))
    }
}