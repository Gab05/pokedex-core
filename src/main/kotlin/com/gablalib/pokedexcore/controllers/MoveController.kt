package com.gablalib.pokedexcore.controllers

import com.gablalib.pokedexcore.services.MoveService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/moves")
object MoveController {

    @GetMapping("")
    fun moves() = MoveService.getAllMoves()

    @GetMapping("/{name}")
    fun moveName(@PathVariable name: String) = MoveService.getMoveByName(name)
}