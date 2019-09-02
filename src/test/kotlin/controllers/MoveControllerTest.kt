package controllers

import com.gablalib.pokedexcore.controllers.MoveController
import com.gablalib.pokedexcore.controllers.requests.MoveRequest
import com.gablalib.pokedexcore.controllers.requests.MovesRequest
import com.gablalib.pokedexcore.filters.MoveFilter
import com.gablalib.pokedexcore.services.requestHandlers.MoveRequestHandler
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import mocks.models.MoveMocks
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.expect

class MoveControllerTest {

    private val move = MoveMocks.tackle()
    private val moves = arrayListOf(move)
    private val aMoveRequest = MoveRequest(move.name)
    private val aMovesRequest = MovesRequest()

    @Before
    fun init() {
        mockkObject(MoveRequestHandler)

        every { MoveRequestHandler.handleMoveRequest(aMoveRequest) } returns move
        every { MoveRequestHandler.handleMovesRequest(aMovesRequest) } returns moves
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun `when requesting all moves`() {
        expect(moves, "should return moves") {
            MoveController.moves(aMovesRequest)
        }

        verify { MoveRequestHandler.handleMovesRequest(aMovesRequest) }
    }

    @Test
    fun `when requesting a single move by name`() {
        expect(move, "should return a move") {
            MoveController.move(move.name)
        }

        verify { MoveRequestHandler.handleMoveRequest(aMoveRequest) }
    }
}