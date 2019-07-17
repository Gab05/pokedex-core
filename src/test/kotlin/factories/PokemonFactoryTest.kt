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
import org.junit.Test
import kotlin.test.assertEquals

class PokemonFactoryTest {

    private val A_POKEMON_NAME = "garchomp"
    private val A_NATIONAL_NUMBER = 445
    private val A_POKEMON_ENTITY = PokemonEntity(
        name = A_POKEMON_NAME,
        stats = Stats(108, 130, 95, 80, 85, 102),
        nationalNumber = A_NATIONAL_NUMBER,
        type = arrayOf("dragon", "ground"),
        levelUpMoves = arrayOf(LevelUpMove("tackle", "1")),
        tmMoves = arrayOf(TmMove("dragon_claw", "02")),
        genderRatio = GenderRatio("50", "50"),
        weight = Weight(lbs = "209.4", kg = "95"),
        captureRate = "45"
    )
    private val POKEMONS_ENTITY = arrayListOf(A_POKEMON_ENTITY, A_POKEMON_ENTITY)

    @Test
    fun whenCreatingEntities_thenCreateIsCalledOnEveryEntity() {
        mockkObject(PokemonFactory)

        PokemonFactory.createAll(POKEMONS_ENTITY)
        verify(exactly = 2) { PokemonFactory.create(A_POKEMON_ENTITY) }

        unmockkObject(PokemonFactory)
    }

    @Test
    fun whenCreatingAnEntity_thenCorrectPokemonIsCreated() {
        val expected = Pokemon(
            name = A_POKEMON_NAME,
            baseStats = Stats(108, 130, 95, 80, 85, 102),
            nationalNumber = A_NATIONAL_NUMBER,
            typing = hashSetOf(Type.DRAGON, Type.GROUND),
            levelUpMoves = arrayListOf(LevelUpMove("tackle", "1")),
            tmMoves = arrayListOf(TmMove("dragon_claw", "02")),
            weight = Weight(lbs = "209.4", kg = "95"),
            genderRatio = GenderRatio("50", "50"),
            captureRate = "45"
        )
        val actual = PokemonFactory.create(A_POKEMON_ENTITY)

        assertEquals(expected.name, actual.name)
        assertEquals(expected.typing, actual.typing)
        assertEquals(expected.nationalNumber, actual.nationalNumber)
        assertEquals(expected.captureRate, actual.captureRate)

        assertEquals(expected.weight.kg, actual.weight.kg)
        assertEquals(expected.weight.lbs, actual.weight.lbs)

        assertEquals(expected.genderRatio.male, actual.genderRatio.male)
        assertEquals(expected.genderRatio.female, actual.genderRatio.female)

        assertEquals(expected.baseStats.hp, actual.baseStats.hp)
        assertEquals(expected.baseStats.atk, actual.baseStats.atk)
        assertEquals(expected.baseStats.def, actual.baseStats.def)
        assertEquals(expected.baseStats.spatk, actual.baseStats.spatk)
        assertEquals(expected.baseStats.spdef, actual.baseStats.spdef)
        assertEquals(expected.baseStats.speed, actual.baseStats.speed)
    }

    @Test
    fun whenCreatingNullEntity_thenDefaultPokemonIsReturned() {
        val expected = Pokemon("")
        val actual = PokemonFactory.create(null)

        assertEquals(expected.name, actual.name)
    }
}