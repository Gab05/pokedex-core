package com.gablalib.pokedexcore.controllers

import com.gablalib.pokedexcore.services.MoveService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
object MoveController {

    @GetMapping("/moves")
    fun moves() = MoveService.getAllMoves()

    @GetMapping("/moves/{name}")
    fun moveName(@PathVariable name: String) = MoveService.getMoveByName(name)
}