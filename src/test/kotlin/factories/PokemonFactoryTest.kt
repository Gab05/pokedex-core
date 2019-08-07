package factories

import com.gablalib.pokedexcore.factories.PokemonFactory
import com.gablalib.pokedexcore.models.move.LevelUpMove
import com.gablalib.pokedexcore.models.move.TmMove
import com.gablalib.pokedexcore.models.pokemon.Pokemon
import com.gablalib.pokedexcore.models.pokemon.gender.GenderRatio
import com.gablalib.pokedexcore.models.pokemon.stats.Stats
import com.gablalib.pokedexcore.models.pokemon.weight.Weight
import com.gablalib.pokedexcore.models.type.Type
import com.gablalib.pokedexcore.repositories.entities.PokemonEntity
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import mocks.PokemonEntityMocks
import mocks.PokemonMocks
import org.junit.Test
import kotlin.test.assertEquals

class PokemonFactoryTest {

    private val SQUIRTLE = PokemonMocks.squirtle()

    private val SQUIRTLE_ENTITY = PokemonEntityMocks.squirtle()
    private val GARCHOMP_ENTITY = PokemonEntityMocks.garchomp()

    @Test
    fun whenCreatingEntities_thenCreateIsCalledOnEveryEntity() {
        mockkObject(PokemonFactory)

        PokemonFactory.createAll(arrayListOf(SQUIRTLE_ENTITY, GARCHOMP_ENTITY))

        verify {
            PokemonFactory.create(SQUIRTLE_ENTITY)
            PokemonFactory.create(GARCHOMP_ENTITY)
        }

        unmockkObject(PokemonFactory)
    }

    @Test
    fun whenCreatingAnEntity_thenCorrectPokemonIsCreated() {
        val expected = SQUIRTLE
        val actual = PokemonFactory.create(SQUIRTLE_ENTITY)

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
    }

    @Test
    fun whenCreatingNullEntity_thenDefaultPokemonIsReturned() {
        val expected = Pokemon("")
        val actual = PokemonFactory.create(null)

        assertEquals(expected.name, actual.name)
    }
}