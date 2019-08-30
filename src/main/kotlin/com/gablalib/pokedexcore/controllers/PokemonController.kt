package com.gablalib.pokedexcore.controllers

import com.gablalib.pokedexcore.controllers.requests.PokemonRequest
import com.gablalib.pokedexcore.controllers.requests.PokemonsRequest
import com.gablalib.pokedexcore.filters.PokemonFilter
import com.gablalib.pokedexcore.models.pokemon.Pokemon
import com.gablalib.pokedexcore.services.requestHandlers.PokemonRequestHandler
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/pokemons")
object PokemonController {

    @GetMapping("")
    fun pokemons(@RequestBody request: PokemonsRequest?): List<Pokemon>
            = PokemonRequestHandler.handlePokemonsRequest(request)

    @GetMapping("/{name}")
    fun pokemonName(@PathVariable name: String): Pokemon {
        val request = PokemonRequest(name)
        return PokemonRequestHandler.handlePokemonRequest(request)
    }

    @GetMapping("/number/{nationalNumber}")
    fun pokemonNationalNumber(@PathVariable nationalNumber: Int): List<Pokemon> {
        val filter = PokemonFilter(nationalNumber = nationalNumber)
        val request = PokemonsRequest(filter)
        return PokemonRequestHandler.handlePokemonsRequest(request)
    }
}