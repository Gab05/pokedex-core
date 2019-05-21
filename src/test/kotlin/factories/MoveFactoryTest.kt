package factories

import com.gablalib.pokedexcore.factories.MoveFactory
import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.models.type.Type
import com.gablalib.pokedexcore.repositories.entities.MoveEntity
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import org.junit.Test
import kotlin.test.assertEquals

class MoveFactoryTest {

    private val A_MOVE_NAME = "dragon_dance"
    private val A_MOVE_TYPE = "dragon"
    private val A_MOVE_ENTITY = MoveEntity(name = A_MOVE_NAME, type = A_MOVE_TYPE)
    private val MOVES_ENTITY = arrayListOf(A_MOVE_ENTITY, A_MOVE_ENTITY)

    @Test
    fun whenCreatingEntities_thenCreateIsCalledOnEveryEntity() {
        mockkObject(MoveFactory)

        MoveFactory.createAll(MOVES_ENTITY)
        verify(exactly = 2) { MoveFactory.create(A_MOVE_ENTITY) }

        unmockkObject(MoveFactory)
    }

    @Test
    fun whenCreatingAnEntity_thenCorrectMoveIsCreated() {
        val expected = Move(A_MOVE_NAME, Type.valueOf(A_MOVE_TYPE.toUpperCase()))
        val actual = MoveFactory.create(A_MOVE_ENTITY)

        assertEquals(expected.name, actual.name)
        assertEquals(expected.type, actual.type)
    }

    @Test
    fun whenCreatingNullEntity_thenDefaultMoveIsReturned() {
        val expected = Move("")
        val actual = MoveFactory.create(null)

        assertEquals(expected.name, actual.name)
    }
}