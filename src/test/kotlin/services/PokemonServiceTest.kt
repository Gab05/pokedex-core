package services

import com.gablalib.pokedexcore.factories.pokemon.PokemonFactory
import com.gablalib.pokedexcore.factories.pokemon.PokemonMongoFilterFactory
import com.gablalib.pokedexcore.filters.PokemonFilter
import com.gablalib.pokedexcore.repositories.pokemon.PokemonMongoRepo
import com.gablalib.pokedexcore.services.PokemonService
import com.gablalib.pokedexcore.services.exceptions.PokemonNotFoundException
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import mocks.entities.PokemonEntityMocks
import mocks.models.PokemonMocks
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.litote.kmongo.EMPTY_BSON
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.expect

class PokemonServiceTest {

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

    @Before
    fun init() {
        mockkObject(PokemonMongoRepo)
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
    }
}