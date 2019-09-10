package com.gablalib.pokedexcore.controllers.requests

import com.gablalib.pokedexcore.filters.AbilityFilter

data class AbilitiesRequest(val filter: AbilityFilter? = null)