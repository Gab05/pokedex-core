package services

import com.gablalib.pokedexcore.factories.MoveFactory
import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.repositories.MoveMongoRepo
import com.gablalib.pokedexcore.repositories.entities.MoveEntity
import com.gablalib.pokedexcore.services.MoveService
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.expect

class MoveServiceTest {

    private val A_MOVE_NAME = "double_kick"
    private val A_MOVE_TYPE = "fighting"
    private val A_MOVE_ENTITY = MoveEntity(
        name = A_MOVE_NAME,
        type = A_MOVE_TYPE,
        category = "PHYSICAL",
        pp = "30",
        power = "30",
        accuracy = "100",
        battleDescription = "",
        battleEffect = "No effect.",
        battleEffectRate = "--")
    private val A_MOVE = Move(A_MOVE_NAME)
    private val MOVES = arrayListOf(A_MOVE, A_MOVE)
    private val MOVES_ENTITY = arrayListOf(A_MOVE_ENTITY, A_MOVE_ENTITY)

    @Before
    fun init() {
        mockkObject(MoveMongoRepo)
        every { MoveMongoRepo.findAll() } returns MOVES_ENTITY
        every { MoveMongoRepo.findByName(A_MOVE_NAME) } returns A_MOVE_ENTITY

        mockkObject(MoveFactory)
        every { MoveFactory.create(A_MOVE_ENTITY) } returns A_MOVE
        every { MoveFactory.createAll(MOVES_ENTITY) } returns MOVES
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun whenGettingAllMoves() {
        expect(MOVES, "should return a Move list") {
            MoveService.getAllMoves()
        }

        verify { MoveMongoRepo.findAll() }
        verify { MoveFactory.createAll(MOVES_ENTITY) }
    }

    @Test
    fun whenGettingMoveByName() {
        expect(A_MOVE, "should return a Move") {
            MoveService.getMoveByName(A_MOVE_NAME)
        }

        verify { MoveMongoRepo.findByName(A_MOVE_NAME) }
        verify { MoveFactory.create(A_MOVE_ENTITY) }
    }
}