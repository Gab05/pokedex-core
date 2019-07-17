package com.gablalib.pokedexcore.repositories.entities

import com.gablalib.pokedexcore.models.move.LevelUpMove
import com.gablalib.pokedexcore.models.move.TmMove
import com.gablalib.pokedexcore.models.pokemon.gender.GenderRatio
import com.gablalib.pokedexcore.models.pokemon.stats.Stats
import com.gablalib.pokedexcore.models.pokemon.weight.Weight

data class PokemonEntity(val name: String,
                         val stats: Stats,
                         val nationalNumber: Int,
                         val type: Array<String>,
                         val weight: Weight,
                         val genderRatio: GenderRatio,
                         val captureRate: String,
                         val levelUpMoves: Array<LevelUpMove>,
                         val tmMoves: Array<TmMove>)