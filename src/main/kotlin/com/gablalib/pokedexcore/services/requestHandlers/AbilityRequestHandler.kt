package com.gablalib.pokedexcore.services.requestHandlers

import com.gablalib.pokedexcore.controllers.requests.AbilitiesRequest
import com.gablalib.pokedexcore.controllers.requests.SimpleRequest
import com.gablalib.pokedexcore.models.pokemon.ability.Ability
import com.gablalib.pokedexcore.services.AbilityService

object AbilityRequestHandler {

    fun handleAbilityRequest(request: SimpleRequest): Ability
            = AbilityService.getAbilityByName(request.name)

    fun handleAbilitiesRequest(request: AbilitiesRequest?): List<Ability> {
        return if (request?.filter == null) AbilityService.getAllAbilities()
        else AbilityService.getAbilitiesByFilter(request.filter)
    }
}