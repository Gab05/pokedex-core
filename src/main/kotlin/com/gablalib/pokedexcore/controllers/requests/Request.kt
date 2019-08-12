package com.gablalib.pokedexcore.controllers.requests

import com.gablalib.pokedexcore.filters.Filter

interface Request<T> {
    val filter: Filter?
}