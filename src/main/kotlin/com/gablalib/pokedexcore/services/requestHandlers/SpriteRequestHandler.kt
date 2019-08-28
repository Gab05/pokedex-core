package com.gablalib.pokedexcore.services.requestHandlers

import com.gablalib.pokedexcore.controllers.requests.PokemonRequest
import com.gablalib.pokedexcore.services.PokemonService

object SpriteRequestHandler {
    fun handleNormalPokemonRequest(request: PokemonRequest): ByteArray
            = PokemonService.getNormalSprite(request.name)

    fun handleShinyPokemonRequest(request: PokemonRequest): ByteArray
            = PokemonService.getShinySprite(request.name)
}