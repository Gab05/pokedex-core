package com.gablalib.pokedexcore.services.requestHandlers

import com.gablalib.pokedexcore.controllers.requests.PokemonsRequest
import com.gablalib.pokedexcore.controllers.requests.SimpleRequest
import com.gablalib.pokedexcore.filters.PokemonFilter
import com.gablalib.pokedexcore.services.PokemonService
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import com.gablalib.pokedexcore.mocks.models.PokemonMocks
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.expect

class PokemonRequestHandlerTest {

    private val garchomp = PokemonMocks.garchomp()
    private val squirtle = PokemonMocks.squirtle()
    private val allPokemons = arrayListOf(garchomp, squirtle)
    private val filteredPokemons = arrayListOf(garchomp)

    private val nonNullFilter = PokemonFilter(names = arrayListOf(garchomp.name))

    private val simpleRequest = SimpleRequest(garchomp.name)
    private val pokemonsRequest = PokemonsRequest(nonNullFilter)
    private val nullFilterRequest = PokemonsRequest()

    @Before
    fun init() {
        mockkObject(PokemonService)

        every { PokemonService.getPokemonByName(garchomp.name) } returns garchomp
        every { PokemonService.getAllPokemons() } returns allPokemons
        every { PokemonService.getPokemonsByFilter(nonNullFilter) } returns filteredPokemons
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun `when handling a simple pokemon request`() {
        expect(garchomp, "should return a single pokemon") {
            PokemonRequestHandler.handlePokemonRequest(simpleRequest)
        }

        verify { PokemonService.getPokemonByName(simpleRequest.name) }
    }

    @Test
    fun `when handling a null pokemons request`() {
        expect(allPokemons, "should return all pokemons") {
            PokemonRequestHandler.handlePokemonsRequest(null)
        }

        verify { PokemonService.getAllPokemons() }
    }

    @Test
    fun `when handling a non-null pokemons request with a null filter`() {
        expect(allPokemons, "should return all pokemons") {
            PokemonRequestHandler.handlePokemonsRequest(nullFilterRequest)
        }

        verify { PokemonService.getAllPokemons() }
    }

    @Test
    fun `when handling a non-null pokemons request with a non-null filter`() {
        expect(filteredPokemons, "should return filtered Moves") {
            PokemonRequestHandler.handlePokemonsRequest(pokemonsRequest)
        }

        verify { PokemonService.getPokemonsByFilter(nonNullFilter) }
    }
}