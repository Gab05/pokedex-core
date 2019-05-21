package controllers

import com.gablalib.pokedexcore.controllers.PokemonController
import com.gablalib.pokedexcore.models.pokemon.Pokemon
import com.gablalib.pokedexcore.services.PokemonService
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.expect

class PokemonControllerTest {

    private val A_POKEMON_NAME = "charmander"
    private val A_NATIONAL_NUMBER = 4

    @Before
    fun init() {
        mockkObject(PokemonService)

        every { PokemonService.getAllPokemons() } returns ArrayList()
        every { PokemonService.getPokemonByName(A_POKEMON_NAME) } returns Pokemon("charmander")
        every { PokemonService.getPokemonsByNationalNumber(any()) } returns ArrayList()
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun whenGettingAllPokemon() {
        expect(ArrayList(), "should return an ArrayList<Pokemon>",
            { PokemonController.pokemons() })
        verify { PokemonService.getAllPokemons() }
    }

    @Test
    fun whenGettingPokemonByName() {
        expect(A_POKEMON_NAME, "should return a Pokemon of same name",
            { PokemonController.pokemonName(A_POKEMON_NAME).name })
        verify { PokemonService.getPokemonByName(A_POKEMON_NAME) }
    }

    @Test
    fun whenGettingPokemonByNationalNumber() {
        expect(ArrayList(), "should return an ArrayList<Pokemon>",
            { PokemonController.pokemonNationalNumber(A_NATIONAL_NUMBER) })
        verify { PokemonService.getPokemonsByNationalNumber(A_NATIONAL_NUMBER) }
    }
}