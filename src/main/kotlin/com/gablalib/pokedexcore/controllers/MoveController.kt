package com.gablalib.pokedexcore.controllers

import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.services.MoveService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/moves")
object MoveController {

    @GetMapping("")
    fun moves(): List<Move> = MoveService.getAllMoves()

    @GetMapping("/{name}")
    fun moveName(@PathVariable name: String): Move = MoveService.getMoveByName(name)
}