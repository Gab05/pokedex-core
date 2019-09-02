package com.gablalib.pokedexcore.filters

class PokemonFilter(
    override val names: List<String>? = null,
    val nationalNumber: Int? = null,
    val type: String? = null,
    val move: String? = null,
    val ability: String? = null
): Filter