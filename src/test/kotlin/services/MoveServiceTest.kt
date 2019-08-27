package services

import com.gablalib.pokedexcore.factories.move.MoveFactory
import com.gablalib.pokedexcore.repositories.move.MoveMongoRepo
import com.gablalib.pokedexcore.services.MoveService
import com.gablalib.pokedexcore.services.exceptions.MoveNotFoundException
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import mocks.MoveEntityMocks
import mocks.MoveMocks
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.expect

class MoveServiceTest {

    private val aMove = MoveMocks.basicMove()
    private val aMoveEntity = MoveEntityMocks.basicMove()
    private val moves = arrayListOf(aMove)
    private val moveEntities = arrayListOf(aMoveEntity)
    private val aNotFoundMoveName = "not_a_move"

    @Before
    fun init() {
        mockkObject(MoveMongoRepo)
        every { MoveMongoRepo.findAll() } returns moveEntities
        every { MoveMongoRepo.findByName(aMove.name) } returns aMoveEntity
        every { MoveMongoRepo.findByName(aNotFoundMoveName) } returns null

        mockkObject(MoveFactory)
        every { MoveFactory.create(aMoveEntity) } returns aMove
        every { MoveFactory.createAll(moveEntities) } returns moves
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun `when getting all moves`() {
        expect(moves, "should return a Move list") {
            MoveService.getAllMoves()
        }

        verify { MoveMongoRepo.findAll() }
        verify { MoveFactory.createAll(moveEntities) }
    }

    @Test
    fun `when getting move by name`() {
        expect(aMove, "should return a Move") {
            MoveService.getMoveByName(aMove.name)
        }

        verify { MoveMongoRepo.findByName(aMove.name) }
        verify { MoveFactory.create(aMoveEntity) }
    }

    @Test
    fun `when getting a move by name that does not exist`() {
        val e = assertFailsWith<MoveNotFoundException> {
            MoveService.getMoveByName(aNotFoundMoveName)
        }

        verify { MoveMongoRepo.findByName(aNotFoundMoveName) }
        assertEquals(e.name, aNotFoundMoveName)
    }
}