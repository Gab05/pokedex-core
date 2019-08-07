package services

import com.gablalib.pokedexcore.factories.PokemonFactory
import com.gablalib.pokedexcore.repositories.PokemonMongoRepo
import com.gablalib.pokedexcore.services.PokemonService
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import mocks.PokemonEntityMocks
import mocks.PokemonMocks
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.expect

class PokemonServiceTest {

    private val SQUIRTLE_ENTITY = PokemonEntityMocks.squirtle()
    private val GARCHOMP_ENTITY = PokemonEntityMocks.garchomp()

    private val SQUIRTLE = PokemonMocks.squirtle()
    private val GARCHOMP = PokemonMocks.garchomp()

    private val GARCHOMP_NAME = "garchomp"
    private val SQUIRTLE_NATIONAL_NUMBER = 7

    private val ENTITIES = arrayListOf(SQUIRTLE_ENTITY, GARCHOMP_ENTITY)
    private val POKEMONS = arrayListOf(SQUIRTLE, GARCHOMP)

    @Before
    fun init() {
        mockkObject(PokemonMongoRepo)
        every { PokemonMongoRepo.findAll() } returns ENTITIES
        every { PokemonMongoRepo.findByName(GARCHOMP_NAME) } returns GARCHOMP_ENTITY
        every { PokemonMongoRepo.findByNationalNumber(SQUIRTLE_NATIONAL_NUMBER) } returns arrayListOf(SQUIRTLE_ENTITY)

        mockkObject(PokemonFactory)
        every { PokemonFactory.create(SQUIRTLE_ENTITY) } returns SQUIRTLE
        every { PokemonFactory.create(GARCHOMP_ENTITY) } returns GARCHOMP
        every { PokemonFactory.createAll(arrayListOf(SQUIRTLE_ENTITY)) } returns arrayListOf(SQUIRTLE)
        every { PokemonFactory.createAll(ENTITIES) } returns POKEMONS
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
        verify { PokemonFactory.createAll(ENTITIES) }
    }

    @Test
    fun whenGettingPokemonByName() {
        expect(GARCHOMP, "should return a Pokemon with matching name") {
            PokemonService.getPokemonByName(GARCHOMP_NAME)
        }

        verify { PokemonMongoRepo.findByName(GARCHOMP_NAME) }
        verify { PokemonFactory.create(GARCHOMP_ENTITY) }
    }

    @Test
    fun whenGettingPokemonByNationalNumber() {
        expect(arrayListOf(SQUIRTLE), "should return a Pokemon list") {
            PokemonService.getPokemonsByNationalNumber(SQUIRTLE_NATIONAL_NUMBER)
        }

        verify { PokemonMongoRepo.findByNationalNumber(SQUIRTLE_NATIONAL_NUMBER) }
        verify { PokemonFactory.createAll(arrayListOf(SQUIRTLE_ENTITY)) }
    }
}