package com.gablalib.pokedexcore.models.pokemon

import com.gablalib.pokedexcore.models.move.LevelUpMove
import com.gablalib.pokedexcore.models.move.TmMove
import com.gablalib.pokedexcore.models.pokemon.ability.Abilities
import com.gablalib.pokedexcore.models.pokemon.breeding.EggGroup
import com.gablalib.pokedexcore.models.pokemon.exp.ExpGrowth
import com.gablalib.pokedexcore.models.pokemon.gender.GenderRatio
import com.gablalib.pokedexcore.models.pokemon.stats.Stats
import com.gablalib.pokedexcore.models.pokemon.weight.Weight
import com.gablalib.pokedexcore.models.type.Type
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

data class Pokemon(val name: String,
                   val nationalNumber: Int = 0,
                   val typing: Set<Type> = HashSet(),
                   val weight: Weight = Weight(),
                   val abilities: Abilities = Abilities(),
                   val levelUpMoves: List<LevelUpMove> = ArrayList(),
                   val tmMoves: List<TmMove> = ArrayList(),
                   val baseStats: Stats = Stats(),
                   val baseExpGrowth: ExpGrowth = ExpGrowth.FAST,
                   val baseEggSteps: Int = 0,
                   val baseHappiness: Int = 0,
                   val genderRatio: GenderRatio = GenderRatio(),
                   val captureRate: String = "255",
                   val eggGroup: List<EggGroup> = ArrayList(),
                   val effortValuesYielded: Stats = Stats()) {

    fun isOfType(type: Type): Boolean {
        return typing.contains(type)
    }
}
