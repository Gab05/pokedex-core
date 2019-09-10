package services

<<<<<<< HEAD

=======
import com.gablalib.pokedexcore.factories.pokemon.PokemonMongoFilterFactory
import com.gablalib.pokedexcore.filters.PokemonFilter
import com.gablalib.pokedexcore.repositories.pokemon.PokemonMongoRepo
>>>>>>> 70b0c6aa7b658364e5fce76481ca1f15087ee923
import com.gablalib.pokedexcore.services.PokemonService
import com.gablalib.pokedexcore.services.exceptions.PokemonNotFoundException
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
<<<<<<< HEAD
=======
>>>>>>> 70b0c6aa7b658364e5fce76481ca1f15087ee923
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.litote.kmongo.EMPTY_BSON
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.expect

class PokemonServiceTest {

<<<<<<< HEAD
    private val SQUIRTLE_ENTITY = PokemonEntityMocks.squirtle()
    private val GARCHOMP_ENTITY = PokemonEntityMocks.garchomp()

    private val SQUIRTLE = PokemonMocks.squirtle()
    private val GARCHOMP = PokemonMocks.garchomp()

    private val GARCHOMP_NAME = "garchomp"
    private val SQUIRTLE_NATIONAL_NUMBER = 7

    private val ENTITIES = arrayListOf(SQUIRTLE_ENTITY, GARCHOMP_ENTITY)
    private val POKEMONS = arrayListOf(SQUIRTLE, GARCHOMP)
=======
    private val squirtleEntity = PokemonEntityMocks.squirtle()
    private val garchompEntity = PokemonEntityMocks.garchomp()
    private val filteredEntities = arrayListOf(squirtleEntity)
    private val allEntities = arrayListOf(squirtleEntity, garchompEntity)

    private val squirtle = PokemonMocks.squirtle()
    private val garchomp = PokemonMocks.garchomp()
    private val filteredPokemons = arrayListOf(squirtle)
    private val allPokemons = arrayListOf(squirtle, garchomp)

    private val pokemonFilter = PokemonFilter()
    private val mongoFilter = EMPTY_BSON
    private val notAPokemonName = "bru"
>>>>>>> 70b0c6aa7b658364e5fce76481ca1f15087ee923

    @Before
    fun init() {
        mockkObject(PokemonMongoRepo)
<<<<<<< HEAD
        every { PokemonMongoRepo.findAll() } returns ENTITIES
        every { PokemonMongoRepo.findByName(GARCHOMP_NAME) } returns GARCHOMP_ENTITY
        every { PokemonMongoRepo.findByNationalNumber(SQUIRTLE_NATIONAL_NUMBER) } returns arrayListOf(SQUIRTLE_ENTITY)

        mockkObject(PokemonFactory)
        every { PokemonFactory.create(SQUIRTLE_ENTITY) } returns SQUIRTLE
        every { PokemonFactory.create(GARCHOMP_ENTITY) } returns GARCHOMP
        every { PokemonFactory.createAll(arrayListOf(SQUIRTLE_ENTITY)) } returns arrayListOf(SQUIRTLE)
        every { PokemonFactory.createAll(ENTITIES) } returns POKEMONS
=======
        every { PokemonMongoRepo.findAll() } returns allEntities
        every { PokemonMongoRepo.findAllByFilter(mongoFilter) } returns filteredEntities
        every { PokemonMongoRepo.findByName(squirtle.name) } returns squirtleEntity
        every { PokemonMongoRepo.findByName(notAPokemonName) } returns null

        mockkObject(PokemonFactory)
        every { PokemonFactory.createAll(allEntities) } returns allPokemons
        every { PokemonFactory.createAll(filteredEntities) } returns filteredPokemons
        every { PokemonFactory.create(squirtleEntity) } returns squirtle

        mockkObject(PokemonMongoFilterFactory)
        every { PokemonMongoFilterFactory.create(pokemonFilter) } returns mongoFilter
>>>>>>> 70b0c6aa7b658364e5fce76481ca1f15087ee923
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun `when getting all pokemons`() {
        expect(allPokemons, "should return a Pokemon list") {
            PokemonService.getAllPokemons()
        }

        verify { PokemonMongoRepo.findAll() }
<<<<<<< HEAD
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
=======
        verify { PokemonFactory.createAll(allEntities) }
    }

    @Test
    fun `when getting a pokemon by name`() {
        expect(squirtle, "should return a Pokemon with matching name") {
            PokemonService.getPokemonByName(squirtle.name)
        }

        verify { PokemonMongoRepo.findByName(squirtle.name) }
        verify { PokemonFactory.create(squirtleEntity) }
    }

    @Test
    fun `when getting a non-existing pokemon by name`() {
        val e = assertFailsWith<PokemonNotFoundException> {
            PokemonService.getPokemonByName(notAPokemonName)
        }

        verify { PokemonMongoRepo.findByName(notAPokemonName) }
        assertEquals(e.name, notAPokemonName)
    }

    @Test
    fun `when getting all pokemons by filter`() {
        expect(filteredPokemons, "should return a list of filtered pokemons") {
            PokemonService.getPokemonsByFilter(pokemonFilter)
        }

        verify { PokemonMongoFilterFactory.create(pokemonFilter) }
        verify { PokemonMongoRepo.findAllByFilter(mongoFilter) }
        verify { PokemonFactory.createAll(filteredEntities) }
>>>>>>> 70b0c6aa7b658364e5fce76481ca1f15087ee923
    }
}