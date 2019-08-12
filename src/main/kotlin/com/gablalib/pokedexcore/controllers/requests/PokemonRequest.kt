package com.gablalib.pokedexcore.controllers.requests

import com.gablalib.pokedexcore.filters.Filter
import com.gablalib.pokedexcore.models.pokemon.Pokemon

data class PokemonRequest(val name: String,
                          override val filter: Filter? = null): Request<Pokemon?>