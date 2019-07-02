package com.gablalib.pokedexcore.models.move

import com.gablalib.pokedexcore.models.type.Type

data class Move(val name: String,
                val type: Type = Type.NORMAL,
                val PP: Int = 0,
                val power: Int = 0,
                val zMovePower: Int = 0,
                val accuracy: Int = 100,
                val battleDescription: String = String(),
                val battleEffect: String? = String(),
                val battleEffectRate: String? = String(),
                val overworldDescription: String = String(),
                val criticalHitRatio: Float = 4.17f,
                val category: MoveCategory = MoveCategory.NONE,
                val priority: String = "0",
                val flags: ArrayList<MoveFlag> = ArrayList())
