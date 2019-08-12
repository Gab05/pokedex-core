package com.gablalib.pokedexcore.controllers.requests

import com.gablalib.pokedexcore.filters.Filter
import com.gablalib.pokedexcore.models.move.Move

data class MoveRequest(val name: String,
                       override val filter: Filter? = null): Request<Move?>