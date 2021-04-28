package com.gablalib.pokedexcore.controllers

import com.gablalib.pokedexcore.models.pokemon.breeding.EggGroup
import com.gablalib.pokedexcore.services.EggGroupService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/eggGroups")
object EggGroupController {

    @GetMapping("/{name}")
    fun pokemonName(@PathVariable name: String): EggGroup
            = EggGroupService.getEggGroupByName(name)

}