package com.gablalib.pokedexcore.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import com.gablalib.pokedexcore.services.PokemonService

@RestController
object PokemonController {

    @GetMapping("/pokemons")
    fun pokemons() = PokemonService.getAllPokemons()

    @GetMapping("/pokemons/{name}")
    fun pokemonName(@PathVariable name: String) = PokemonService.getPokemonByName(name)

    @GetMapping("/pokemons/number/{nationalNumber}")
    fun pokemonNationalNumber(@PathVariable nationalNumber: Int)
            = PokemonService.getPokemonsByNationalNumber(nationalNumber)
}