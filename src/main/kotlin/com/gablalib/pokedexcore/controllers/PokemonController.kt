package com.gablalib.pokedexcore.controllers

import com.gablalib.pokedexcore.services.PokemonService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/pokemons")
object PokemonController {

    @GetMapping("/")
    fun pokemons() = PokemonService.getAllPokemons()

    @GetMapping("/{name}")
    fun pokemonName(@PathVariable name: String) = PokemonService.getPokemonByName(name)

    @GetMapping("/number/{nationalNumber}")
    fun pokemonNationalNumber(@PathVariable nationalNumber: Int)
            = PokemonService.getPokemonsByNationalNumber(nationalNumber)
}