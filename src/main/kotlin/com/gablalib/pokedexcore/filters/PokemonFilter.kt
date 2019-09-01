package com.gablalib.pokedexcore.filters

class PokemonFilter(
    override val names: List<String>? = null,
    val nationalNumber: Int? = null,
    val types: List<String>? = null,
    val moves: List<String>? = null,
    val abilities: List<String>? = null
): Filter