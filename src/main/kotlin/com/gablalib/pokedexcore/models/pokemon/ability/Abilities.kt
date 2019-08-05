package com.gablalib.pokedexcore.models.pokemon.ability

class Abilities(val first: String = String(),
                val second: String = String(),
                val hidden: String = String()) {

    override fun equals(other: Any?): Boolean {
        return if (other is Abilities) {
            this.first.equals(other.first)
                    && this.second.equals(other.second)
                    && this.hidden.equals(other.hidden)
        } else false
    }
}