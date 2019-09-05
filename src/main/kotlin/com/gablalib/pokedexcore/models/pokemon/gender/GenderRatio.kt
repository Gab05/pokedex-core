package com.gablalib.pokedexcore.models.pokemon.gender

data class GenderRatio(val male: String = "50",
                  val female: String = "50") {

    override fun equals(other: Any?): Boolean {
        return if (other is GenderRatio) {
            this.male == other.male &&
            this.female == other.female
        } else false
    }
}