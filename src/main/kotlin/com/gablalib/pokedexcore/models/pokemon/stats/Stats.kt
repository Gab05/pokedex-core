package com.gablalib.pokedexcore.models.pokemon.stats

class Stats(val hp: Int = 0,
            val atk: Int = 0,
            val def: Int = 0,
            val spatk: Int = 0,
            val spdef: Int = 0,
            val speed: Int = 0) {

    override fun equals(other: Any?): Boolean {
        return if (other is Stats) {
            this.hp.equals(other.hp) &&
            this.atk.equals(other.atk) &&
            this.def.equals(other.def) &&
            this.spatk.equals(other.spatk) &&
            this.spdef.equals(other.spdef) &&
            this.speed.equals(other.speed)
        } else false
    }
}