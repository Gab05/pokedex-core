package com.gablalib.pokedexcore.factories

import com.gablalib.pokedexcore.factories.pokemon.PokemonFactory
import io.mockk.mockkObject
import io.mockk.verify
import com.gablalib.pokedexcore.mocks.entities.PokemonEntityMocks
import com.gablalib.pokedexcore.mocks.models.PokemonMocks
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PokemonFactoryTest {

    private val squirtle = PokemonMocks.squirtle()

    private val squirtleEntity = PokemonEntityMocks.squirtle()
    private val garchompEntity = PokemonEntityMocks.garchomp()

    @Test
    fun whenCreatingEntities_thenCreateIsCalledOnEveryEntity() {
        mockkObject(PokemonFactory, block = {
            PokemonFactory.createAll(arrayListOf(squirtleEntity, garchompEntity))

            verify {
                PokemonFactory.create(squirtleEntity)
                PokemonFactory.create(garchompEntity)
            }
        })
    }

    @Test
    fun whenCreatingAnEntity_thenCorrectPokemonIsCreated() {
        val expected = squirtle
        val actual = PokemonFactory.create(squirtleEntity)

        assertEquals(expected.name, actual.name)
        assertEquals(expected.typing, actual.typing)
        assertEquals(expected.nationalNumber, actual.nationalNumber)
        assertEquals(expected.abilities, actual.abilities)
        assertEquals(expected.levelUpMoves, actual.levelUpMoves)
        assertEquals(expected.tmMoves, actual.tmMoves)
        assertEquals(expected.eggMoves, actual.eggMoves)
        assertEquals(expected.captureRate, actual.captureRate)
        assertEquals(expected.weight, actual.weight)
        assertEquals(expected.genderRatio, actual.genderRatio)
        assertEquals(expected.baseStats, actual.baseStats)
        assertEquals(expected.eggGroups, actual.eggGroups)
    }
}