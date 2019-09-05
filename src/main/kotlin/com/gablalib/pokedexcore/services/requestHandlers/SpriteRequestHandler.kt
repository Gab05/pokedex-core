package com.gablalib.pokedexcore.services.requestHandlers

import com.gablalib.pokedexcore.controllers.requests.SimpleRequest
import com.gablalib.pokedexcore.services.SpriteService

object SpriteRequestHandler {
    fun handleNormalPokemonRequest(request: SimpleRequest): ByteArray
            = SpriteService.getNormalPokemonSprite(request.name)

    fun handleShinyPokemonRequest(request: SimpleRequest): ByteArray
            = SpriteService.getShinyPokemonSprite(request.name)
}