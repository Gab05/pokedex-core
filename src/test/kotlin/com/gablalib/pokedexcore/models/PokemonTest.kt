package com.gablalib.pokedexcore.models

import com.gablalib.pokedexcore.models.type.Type
import com.gablalib.pokedexcore.mocks.models.PokemonMocks
import org.junit.jupiter.api.Test
import kotlin.test.expect


class PokemonTest {

    private val squirtle = PokemonMocks.squirtle()

    @Test
    fun isOfType() {
        expect(true, "when squirtle is of type WATER") { squirtle.isOfType(Type.WATER) }
        expect(false, "when squirtle is of type FIRE") { squirtle.isOfType(Type.FIRE) }
    }
}