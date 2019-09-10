package com.gablalib.pokedexcore.controllers

import com.gablalib.pokedexcore.controllers.requests.MovesRequest
import com.gablalib.pokedexcore.controllers.requests.SimpleRequest
import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.services.requestHandlers.MoveRequestHandler
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/moves")
object MoveController {

    @PostMapping("")
    fun moves(@RequestBody request: MovesRequest?): List<Move>
            = MoveRequestHandler.handleMovesRequest(request)

    @GetMapping("/{name}")
    fun move(@PathVariable name: String): Move
            = MoveRequestHandler.handleMoveRequest(SimpleRequest(name))
}