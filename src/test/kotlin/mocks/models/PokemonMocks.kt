package mocks.models

import com.gablalib.pokedexcore.models.move.LevelUpMove
import com.gablalib.pokedexcore.models.move.TmMove
import com.gablalib.pokedexcore.models.pokemon.Pokemon
import com.gablalib.pokedexcore.models.pokemon.ability.Abilities
import com.gablalib.pokedexcore.models.pokemon.gender.GenderRatio
import com.gablalib.pokedexcore.models.pokemon.stats.Stats
import com.gablalib.pokedexcore.models.pokemon.weight.Weight
import com.gablalib.pokedexcore.models.type.Type

class PokemonMocks {
    companion object {
        fun basicPokemon(): Pokemon { return squirtle()
        }
        fun pokemonWithAMegaEvolution(): Pokemon { return garchomp()
        }
        fun pokemonWithTwoMegaEvolutions(){}
        fun aMegaEvolution(){}
        fun aPokemonWithAnAlolanForm(){}
        fun anAlolanPokemon(){}
        fun aPokemonWithAnAlternateForm(){}
        fun aPokemonWithManyAlternateForms(){}
        fun anAlternateFormPokemon(){}

        fun squirtle(): Pokemon {
            return Pokemon(
                name = "squirtle",
                baseStats = Stats(44, 48, 65, 50, 64, 43),
                nationalNumber = 7,
                abilities = Abilities("torrent", hidden = "rain_dish"),
                typing = hashSetOf(Type.WATER),
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
                captureRate = "45"
            )
        }

        fun garchomp(): Pokemon {
            return Pokemon(
                name = "garchomp",
                baseStats = Stats(108, 130, 95, 80, 85, 102),
                nationalNumber = 445,
                abilities = Abilities("sand_veil", hidden = "rough_skin"),
                typing = hashSetOf(Type.DRAGON, Type.GROUND),
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
                weight = Weight(lbs = "209.4", kg = "95"),
                genderRatio = GenderRatio("50", "50"),
                captureRate = "45"
            )
        }
    }
}