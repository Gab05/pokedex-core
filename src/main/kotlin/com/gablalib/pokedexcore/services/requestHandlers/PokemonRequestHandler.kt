package com.gablalib.pokedexcore.services.requestHandlers

import com.gablalib.pokedexcore.controllers.requests.PokemonRequest
import com.gablalib.pokedexcore.controllers.requests.PokemonsRequest
import com.gablalib.pokedexcore.models.pokemon.Pokemon
import com.gablalib.pokedexcore.services.PokemonService

object PokemonRequestHandler {

    fun handlePokemonRequest(request: PokemonRequest): Pokemon
            = PokemonService.getPokemonByName(request.name)

    fun handlePokemonsRequest(request: PokemonsRequest?): List<Pokemon> {
        return if (request?.filter == null)
            PokemonService.getAllPokemons()
        else
            PokemonService.getPokemonsByFilter(request.filter)
    }
}