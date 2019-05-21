package com.gablalib.pokedexcore.models.pokemon.nature

import com.gablalib.pokedexcore.models.pokemon.stats.StatName

class Nature(val name: NatureName = NatureName.HARDY,
             val increasedStat: StatName = StatName.ATK,
             val decreasedStat: StatName = StatName.ATK)