package com.gablalib.pokedexcore.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import com.gablalib.pokedexcore.services.PokemonService
import org.springframework.web.bind.annotation.RequestMapping

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