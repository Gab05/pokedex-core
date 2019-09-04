package controllers

import com.gablalib.pokedexcore.controllers.PokemonController
import com.gablalib.pokedexcore.controllers.requests.PokemonRequest
import com.gablalib.pokedexcore.controllers.requests.PokemonsRequest
import com.gablalib.pokedexcore.filters.PokemonFilter
import com.gablalib.pokedexcore.services.requestHandlers.PokemonRequestHandler
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import mocks.models.PokemonMocks
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.expect

class PokemonControllerTest {

    private val aPokemon = PokemonMocks.squirtle()
    private val anotherPokemon = PokemonMocks.garchomp()
    private val pokemons = arrayListOf(aPokemon, anotherPokemon)
    private val nationalNumberpokemons = arrayListOf(aPokemon)
    private val aPokemonRequest = PokemonRequest(aPokemon.name)
    private val aPokemonNationalNumberFilter = PokemonFilter(nationalNumber = aPokemon.nationalNumber)
    private val aPokemonNationalNumberReqest = PokemonsRequest(aPokemonNationalNumberFilter)
    private val allPokemonsRequest = PokemonsRequest()

    @Before
    fun init() {
        mockkObject(PokemonRequestHandler)

        every { PokemonRequestHandler.handlePokemonRequest(aPokemonRequest) } returns aPokemon
        every { PokemonRequestHandler.handlePokemonsRequest(allPokemonsRequest) } returns pokemons
        every {
            PokemonRequestHandler.handlePokemonsRequest(aPokemonNationalNumberReqest)
        } returns nationalNumberpokemons
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun `when requesting all pokemons`() {
        expect(pokemons, "should return an ArrayList<Pokemon>",
            { PokemonController.pokemons(allPokemonsRequest) })

        verify { PokemonRequestHandler.handlePokemonsRequest(allPokemonsRequest) }
    }

    @Test
    fun `when requesting a pokemon by name`() {
        expect(aPokemon, "should return a Pokemon",
            { PokemonController.pokemonName(aPokemon.name) })

        verify { PokemonRequestHandler.handlePokemonRequest(aPokemonRequest) }
    }

    @Test
    fun `when requesting pokemons by national number`() {
        expect(nationalNumberpokemons, "should return a Pokemon of same name",
            { PokemonController.pokemonNationalNumber(aPokemon.nationalNumber) })

        verify { PokemonRequestHandler.handlePokemonsRequest(aPokemonNationalNumberReqest) }
    }
}