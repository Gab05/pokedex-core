package com.gablalib.pokedexcore.controllers.requests

import com.gablalib.pokedexcore.filters.MoveFilter
import com.gablalib.pokedexcore.models.move.Move

data class MovesRequest(override val filter: MoveFilter?): Request<List<Move>>