package com.gablalib.pokedexcore.controllers

import com.gablalib.pokedexcore.controllers.requests.AbilitiesRequest
import com.gablalib.pokedexcore.controllers.requests.SimpleRequest
import com.gablalib.pokedexcore.models.pokemon.ability.Ability
import com.gablalib.pokedexcore.services.requestHandlers.AbilityRequestHandler
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/abilities")
object AbilityController {

    @GetMapping("")
    fun abilities(@RequestBody request: AbilitiesRequest?): List<Ability>
            = AbilityRequestHandler.handleAbilitiesRequest(request)

    @GetMapping("/{name}")
    fun ability(@PathVariable name: String): Ability
            = AbilityRequestHandler.handleAbilityRequest(SimpleRequest(name))
}