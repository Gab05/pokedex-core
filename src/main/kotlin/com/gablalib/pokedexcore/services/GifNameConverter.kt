package com.gablalib.pokedexcore.services

object GifNameConverter {

    fun getGifNameFromPokemonName(pokemonName: String): String {
        if (pokemonName.startsWith("mega_")) {
            if (pokemonName.endsWith("_x"))
                return (pokemonName.substring(5, pokemonName.length-2) + "_megax").replace('_', '-')
            if (pokemonName.endsWith("_y"))
                return (pokemonName.substring(5, pokemonName.length-2) + "_megay").replace('_', '-')
            return (pokemonName.substring(5) + "_mega").replace('_', '-')
        }
        return pokemonName.replace('_', '-')
    }
}