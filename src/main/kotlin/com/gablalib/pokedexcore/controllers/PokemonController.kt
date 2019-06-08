package com.gablalib.pokedexcore.controllers

import com.gablalib.pokedexcore.services.PokemonService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/pokemons")
object PokemonController {

    @GetMapping("")
    fun pokemons() = PokemonService.getAllPokemons()

    @GetMapping("/{name}")
    fun pokemonName(@PathVariable name: String) = PokemonService.getPokemonByName(name)

    @GetMapping("/number/{nationalNumber}")
    fun pokemonNationalNumber(@PathVariable nationalNumber: Int)
            = PokemonService.getPokemonsByNationalNumber(nationalNumber)

    @GetMapping("/{name}/sprite/normal",
        produces = [MediaType.IMAGE_GIF_VALUE]
    )
    fun pokemonNormalSprite(@PathVariable name: String) = PokemonService.getNormalSprite(name)

    @GetMapping("/{name}/sprite/shiny",
        produces = [MediaType.IMAGE_GIF_VALUE]
    )
    fun pokemonShinySprite(@PathVariable name: String) = PokemonService.getShinySprite(name)
}