package com.gablalib.pokedexcore.controllers

import com.gablalib.pokedexcore.controllers.requests.MoveRequest
import com.gablalib.pokedexcore.controllers.requests.MovesRequest
import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.services.MoveService
import com.gablalib.pokedexcore.services.requestHandlers.MoveRequestHandler
import com.gablalib.pokedexcore.services.responses.MoveResponse
import com.gablalib.pokedexcore.services.responses.MovesResponse
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/moves")
object MoveController {

    @GetMapping("/{name}")
    fun move(@PathVariable name: String): MoveResponse {
        return MoveRequestHandler.handleMoveRequest(MoveRequest(name))
    }

    @GetMapping("")
    fun moves(@RequestBody request: MovesRequest?): MovesResponse {
        return MoveRequestHandler.handleMovesRequest(request)
    }
}