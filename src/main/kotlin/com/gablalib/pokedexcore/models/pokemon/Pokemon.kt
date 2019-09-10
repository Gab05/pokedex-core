package com.gablalib.pokedexcore.models.pokemon

import com.gablalib.pokedexcore.models.move.LevelUpMove
import com.gablalib.pokedexcore.models.move.TmMove
import com.gablalib.pokedexcore.models.pokemon.ability.Abilities
import com.gablalib.pokedexcore.models.pokemon.gender.GenderRatio
import com.gablalib.pokedexcore.models.pokemon.stats.Stats
import com.gablalib.pokedexcore.models.pokemon.weight.Weight
import com.gablalib.pokedexcore.models.type.Type

data class Pokemon(val name: String,
                   val nationalNumber: Int = 0,
                   val typing: Set<Type> = HashSet(),
                   val weight: Weight = Weight(),
                   val abilities: Abilities = Abilities(),
                   val levelUpMoves: List<LevelUpMove> = ArrayList(),
                   val tmMoves: List<TmMove> = ArrayList(),
                   val eggMoves: List<String> = ArrayList(),
                   val baseStats: Stats = Stats(),
                   val genderRatio: GenderRatio = GenderRatio(),
                   val captureRate: String = "255") {

    fun isOfType(type: Type): Boolean {
        return typing.contains(type)
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Pokemon) {
            name == other.name &&
            nationalNumber == other.nationalNumber &&
            typing == other.typing &&
            weight == other.weight &&
            abilities == other.abilities &&
            levelUpMoves == other.levelUpMoves &&
            tmMoves == other.tmMoves &&
            eggMoves == other.eggMoves &&
            baseStats == other.baseStats &&
            genderRatio == other.genderRatio &&
            captureRate == other.captureRate
        } else false
    }
}
