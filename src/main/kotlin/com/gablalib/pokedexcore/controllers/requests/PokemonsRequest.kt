package com.gablalib.pokedexcore.controllers.requests

import com.gablalib.pokedexcore.filters.PokemonFilter
import com.gablalib.pokedexcore.models.pokemon.Pokemon

data class PokemonsRequest(override val filter: PokemonFilter?): Request<List<Pokemon>>