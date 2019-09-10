package com.gablalib.pokedexcore.controllers.requests

import com.gablalib.pokedexcore.filters.MoveFilter

data class MovesRequest(val filter: MoveFilter? = null)
