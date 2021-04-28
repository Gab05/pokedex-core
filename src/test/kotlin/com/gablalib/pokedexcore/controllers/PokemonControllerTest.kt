package com.gablalib.pokedexcore.controllers

import com.gablalib.pokedexcore.controllers.requests.PokemonsRequest
import com.gablalib.pokedexcore.controllers.requests.SimpleRequest
import com.gablalib.pokedexcore.mocks.models.PokemonMocks
import com.gablalib.pokedexcore.services.requestHandlers.PokemonRequestHandler
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import kotlin.test.expect

class PokemonControllerTest {
    companion object {
        private val aPokemon = PokemonMocks.squirtle()
        private val anotherPokemon = PokemonMocks.garchomp()
        private val pokemons = arrayListOf(aPokemon, anotherPokemon)
        private val aPokemonRequest = SimpleRequest(aPokemon.name)
        private val pokemonsRequest = PokemonsRequest()

        @BeforeAll
        @JvmStatic
        fun init() {
            mockkObject(PokemonRequestHandler)

            every { PokemonRequestHandler.handlePokemonRequest(aPokemonRequest) } returns aPokemon
            every { PokemonRequestHandler.handlePokemonsRequest(any()) } returns pokemons
        }

        @AfterAll
        @JvmStatic
        fun exit() {
            unmockkAll()
        }
    }

    @Test
    fun `when requesting all pokemons`() {
        expect(pokemons, "should return an ArrayList<Pokemon>",
            { PokemonController.pokemons(pokemonsRequest) })

        verify { PokemonRequestHandler.handlePokemonsRequest(pokemonsRequest) }
    }

    @Test
    fun `when requesting a pokemon by name`() {
        expect(aPokemon, "should return a Pokemon",
            { PokemonController.pokemonName(aPokemon.name) })

        verify { PokemonRequestHandler.handlePokemonRequest(aPokemonRequest) }
    }

    @Test
    fun `when requesting pokemons by national number`() {
        expect(pokemons, "should return an arrayList of Pokemon",
            { PokemonController.pokemonNationalNumber(aPokemon.nationalNumber) })

        verify { PokemonRequestHandler.handlePokemonsRequest(any()) }
    }
}