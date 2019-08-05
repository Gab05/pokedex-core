package com.gablalib.pokedexcore.models.pokemon.gender

class GenderRatio(val male: String = "50",
                  val female: String = "50") {
    override fun equals(other: Any?): Boolean {
        return if (other is GenderRatio) {
            this.male.equals(other.male) &&
            this.female.equals(other.female)
        } else false
    }
}