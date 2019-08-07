package com.gablalib.pokedexcore.controllers

import com.gablalib.pokedexcore.controllers.requests.MovesRequest
import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.services.MoveService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/moves")
object MoveController {

    @GetMapping("/{name}")
    fun moveName(@PathVariable name: String): Move = MoveService.getMoveByName(name)

    @GetMapping("")
    fun moves(@RequestBody request: MovesRequest): List<Move> = MoveService.getAllMoves()
}