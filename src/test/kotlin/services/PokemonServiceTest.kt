package services

import com.gablalib.pokedexcore.factories.PokemonFactory
import com.gablalib.pokedexcore.models.pokemon.Pokemon
import com.gablalib.pokedexcore.models.pokemon.gender.GenderRatio
import com.gablalib.pokedexcore.models.pokemon.stats.Stats
import com.gablalib.pokedexcore.models.pokemon.weight.Weight
import com.gablalib.pokedexcore.repositories.PokemonMongoRepo
import com.gablalib.pokedexcore.repositories.entities.PokemonEntity
import com.gablalib.pokedexcore.services.PokemonService
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.expect

class PokemonServiceTest {

    private val A_POKEMON_NAME = "squirtle"
    private val A_NATIONAL_NUMBER = 9
    private val A_POKEMON_ENTITY = PokemonEntity(
        name = A_POKEMON_NAME,
        stats = Stats(),
        nationalNumber = A_NATIONAL_NUMBER,
        type = arrayOf("water"),
        genderRatio = GenderRatio(),
        weight = Weight(),
        captureRate = String()
        )
    private val A_POKEMON = Pokemon(A_POKEMON_NAME)
    private val POKEMONS = arrayListOf(A_POKEMON, A_POKEMON)
    private val POKEMONS_ENTITY = arrayListOf(A_POKEMON_ENTITY, A_POKEMON_ENTITY)

    @Before
    fun init() {
        mockkObject(PokemonMongoRepo)
        every { PokemonMongoRepo.findAll() } returns POKEMONS_ENTITY
        every { PokemonMongoRepo.findByName(A_POKEMON_NAME) } returns A_POKEMON_ENTITY
        every { PokemonMongoRepo.findByNationalNumber(A_NATIONAL_NUMBER) } returns POKEMONS_ENTITY

        mockkObject(PokemonFactory)
        every { PokemonFactory.create(A_POKEMON_ENTITY) } returns A_POKEMON
        every { PokemonFactory.createAll(POKEMONS_ENTITY) } returns POKEMONS
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun whenGettingAllPokemons() {
        expect(POKEMONS, "should return a Pokemon list") {
            PokemonService.getAllPokemons()
        }

        verify { PokemonMongoRepo.findAll() }
        verify { PokemonFactory.createAll(POKEMONS_ENTITY) }
    }

    @Test
    fun whenGettingPokemonByName() {
        expect(A_POKEMON, "should return a Pokemon with matching name") {
            PokemonService.getPokemonByName(A_POKEMON_NAME)
        }

        verify { PokemonMongoRepo.findByName(A_POKEMON_NAME) }
        verify { PokemonFactory.create(A_POKEMON_ENTITY) }
    }

    @Test
    fun whenGettingPokemonByNationalNumber() {
        expect(POKEMONS, "should return a Pokemon list") {
            PokemonService.getPokemonsByNationalNumber(A_NATIONAL_NUMBER)
        }

        verify { PokemonMongoRepo.findByNationalNumber(A_NATIONAL_NUMBER) }
        verify { PokemonFactory.createAll(POKEMONS_ENTITY) }
    }
}