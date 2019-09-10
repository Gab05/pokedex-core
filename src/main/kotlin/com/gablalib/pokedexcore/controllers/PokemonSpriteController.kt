package com.gablalib.pokedexcore.controllers

import com.gablalib.pokedexcore.controllers.requests.SimpleRequest
import com.gablalib.pokedexcore.services.requestHandlers.SpriteRequestHandler
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/pokemons")
object PokemonSpriteController {

    @GetMapping("/{name}/sprite/normal",
        produces = [MediaType.IMAGE_GIF_VALUE])
    fun pokemonNormalSprite(@PathVariable name: String): ByteArray
            = SpriteRequestHandler.handleNormalPokemonRequest(SimpleRequest(name))

    @GetMapping("/{name}/sprite/shiny",
        produces = [MediaType.IMAGE_GIF_VALUE])
    fun pokemonShinySprite(@PathVariable name: String): ByteArray
            = SpriteRequestHandler.handleShinyPokemonRequest(SimpleRequest(name))

}