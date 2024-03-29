package com.gablalib.pokedexcore.mocks.entities

import com.gablalib.pokedexcore.models.move.LevelUpMove
import com.gablalib.pokedexcore.models.move.TmMove
import com.gablalib.pokedexcore.models.pokemon.ability.Abilities
import com.gablalib.pokedexcore.models.pokemon.gender.GenderRatio
import com.gablalib.pokedexcore.models.pokemon.stats.Stats
import com.gablalib.pokedexcore.models.pokemon.weight.Weight
import com.gablalib.pokedexcore.repositories.entities.PokemonEntity

class PokemonEntityMocks {
    companion object {
        fun basicPokemon(): PokemonEntity { return squirtle() }
        fun pokemonWithAMegaEvolution(): PokemonEntity { return garchomp() }
        fun pokemonWithTwoMegaEvolutions(){}
        fun aMegaEvolution(){}
        fun aPokemonWithAnAlolanForm(){}
        fun anAlolanPokemon(){}
        fun aPokemonWithAnAlternateForm(){}
        fun aPokemonWithManyAlternateForms(){}
        fun anAlternateFormPokemon(){}

        fun squirtle(): PokemonEntity {
            return PokemonEntity(
                name = "squirtle",
                stats = Stats(44, 48, 65, 50, 64, 43),
                nationalNumber = 7,
                abilities = Abilities("torrent", hidden = "rain_dish"),
                type = arrayListOf("water"),
                levelUpMoves = arrayListOf(
                    LevelUpMove("tackle", "1"),
                    LevelUpMove("tail_whip", "4"),
                    LevelUpMove("water_gun", "7")
                ),
                tmMoves = arrayListOf(
                    TmMove("blizzard", "14"),
                    TmMove("scald", "55")
                ),
                eggMoves = arrayListOf(
                    "aqua_jet",
                    "aqua_ring",
                    "aura_sphere"
                ),
                weight = Weight(lbs = "19.8", kg = "9"),
                genderRatio = GenderRatio("87.5", "12.5"),
                captureRate = "45",
                eggGroups = arrayListOf("water_1", "monster")
            )
        }

        fun garchomp(): PokemonEntity {
            return PokemonEntity(
                name = "garchomp",
                stats = Stats(108, 130, 95, 80, 85, 102),
                nationalNumber = 445,
                abilities = Abilities("sand_veil", hidden = "rough_skin"),
                type = arrayListOf("dragon", "ground"),
                levelUpMoves = arrayListOf(
                    LevelUpMove("crunch", "1"),
                    LevelUpMove("sand_attack", "3"),
                    LevelUpMove("dragon_rage", "7"),
                    LevelUpMove("sandstorm", "13"),
                    LevelUpMove("take_down", "15"),
                    LevelUpMove("sand_tomb", "19"),
                    LevelUpMove("slash", "28"),
                    LevelUpMove("dragon_claw", "33"),
                    LevelUpMove("dig", "40"),
                    LevelUpMove("dragon_rush", "55")
                ),
                tmMoves = arrayListOf(
                    TmMove("dragon_claw", "02"),
                    TmMove("earthquake", "26")
                ),
                eggMoves = arrayListOf(
                    "body_slam",
                    "double_edge",
                    "iron_head"
                ),
                genderRatio = GenderRatio("50", "50"),
                weight = Weight(lbs = "209.4", kg = "95"),
                captureRate = "45",
                eggGroups = arrayListOf("dragon", "field")
            )
        }
    }
}