package com.gablalib.pokedexcore.filters

class PokemonFilter(
    override val names: List<String>? = ArrayList(),
    val nationalNumber: Int?
): Filter