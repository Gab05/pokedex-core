package com.gablalib.pokedexcore.models.move

import com.gablalib.pokedexcore.models.type.Type

data class Move(val name: String,
                val type: Type = Type.NORMAL,
                val power: Int = 0,
                val zMovePower: Int = 0,
                val accuracy: Int = 100,
                val description: String = String(),
                val battleEffect: String = String(),
                val battleEffectRatio: Float = 0f,
                val overworldEffect: String = String(),
                val baseCriticalHitRatio: Float = 4.17f,
                val maxPP: Int = 40,
                val category: MoveCategory = MoveCategory.OTHER,
                val priority: Int = 0,
                val flags: ArrayList<MoveFlag> = ArrayList())
