package services

import com.gablalib.pokedexcore.factories.pokemon.PokemonFactory
import com.gablalib.pokedexcore.repositories.pokemon.PokemonMongoRepo
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

    private val squirtleEntity = PokemonEntityMocks.squirtle()
    private val garchompEntity = PokemonEntityMocks.garchomp()

    private val squirtle = PokemonMocks.squirtle()
    private val garchomp = PokemonMocks.garchomp()

    private val entities = arrayListOf(squirtleEntity, garchompEntity)
    private val pokemons = arrayListOf(squirtle, garchomp)

    @Before
    fun init() {
        mockkObject(PokemonMongoRepo)
        every { PokemonMongoRepo.findAll() } returns entities
        every { PokemonMongoRepo.findByName(garchomp.name) } returns garchompEntity

        mockkObject(PokemonFactory)
        every { PokemonFactory.create(squirtleEntity) } returns squirtle
        every { PokemonFactory.create(garchompEntity) } returns garchomp
        every { PokemonFactory.createAll(arrayListOf(squirtleEntity)) } returns arrayListOf(squirtle)
        every { PokemonFactory.createAll(entities) } returns pokemons
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun `when getting all pokemons`() {
        expect(pokemons, "should return a Pokemon list") {
            PokemonService.getAllPokemons()
        }

        verify { PokemonMongoRepo.findAll() }
        verify { PokemonFactory.createAll(entities) }
    }

    @Test
    fun `when getting a pokemon by name`() {
        expect(garchomp, "should return a Pokemon with matching name") {
            PokemonService.getPokemonByName(garchomp.name)
        }

        verify { PokemonMongoRepo.findByName(garchomp.name) }
        verify { PokemonFactory.create(garchompEntity) }
    }

    @Test
    fun `when getting all pokemons by filter`() {}
}