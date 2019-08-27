package controllers

import com.gablalib.pokedexcore.controllers.MoveController
import com.gablalib.pokedexcore.controllers.requests.MoveRequest
import com.gablalib.pokedexcore.controllers.requests.MovesRequest
import com.gablalib.pokedexcore.filters.MoveFilter
import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.services.requestHandlers.MoveRequestHandler
import com.gablalib.pokedexcore.services.responses.MoveResponse
import com.gablalib.pokedexcore.services.responses.MovesResponse
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.expect

class MoveControllerTest {

    private val aMoveName = "tackle"
    private val aMoveFilter = MoveFilter(arrayListOf(aMoveName))
    private val aMoveRequest = MoveRequest(aMoveName)
    private val aMovesRequest = MovesRequest(aMoveFilter)
    private val aMoveResponse = MoveResponse(Move(aMoveName))
    private val aMovesResponse = MovesResponse(arrayListOf(Move(aMoveName)))

    @Before
    fun init() {
        mockkObject(MoveRequestHandler)

        every { MoveRequestHandler.handleMoveRequest(aMoveRequest) } returns aMoveResponse
        every { MoveRequestHandler.handleMovesRequest(aMovesRequest) } returns aMovesResponse
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun whenRequestingAMoveByName() {
        expect(aMoveResponse, "should return a MoveResponse") {
            MoveController.move(aMoveName)
        }

        verify { MoveRequestHandler.handleMoveRequest(aMoveRequest) }
    }

    @Test
    fun whenRequestingAllMoves() {
        expect(aMovesResponse, "should return a MovesResponse") {
            MoveController.moves(aMovesRequest)
        }

        verify { MoveRequestHandler.handleMovesRequest(aMovesRequest) }
    }
}