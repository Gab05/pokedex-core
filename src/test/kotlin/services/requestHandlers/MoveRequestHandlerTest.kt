package services.requestHandlers

import com.gablalib.pokedexcore.controllers.requests.MoveRequest
import com.gablalib.pokedexcore.controllers.requests.MovesRequest
import com.gablalib.pokedexcore.filters.MoveFilter
import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.services.MoveService
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

class MoveRequestHandlerTest {

    private val move = MoveMocks.tackle()
    private val allMoves = arrayListOf(move)
    private val filteredMoves = ArrayList<Move>()
    private val aMoveRequest = MoveRequest(move.name)
    private val aMoveFilter = MoveFilter(arrayListOf(move.name))
    private val aMovesRequest = MovesRequest(null)
    private val aFilterMovesRequest = MovesRequest(aMoveFilter)

    @Before
    fun init() {
        mockkObject(MoveService)

        every { MoveService.getAllMoves() } returns allMoves
        every { MoveService.getMoveByName(move.name) } returns move
        every { MoveService.getMovesByFilter(aMoveFilter) } returns filteredMoves
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun `when handling a MoveRequest`() {
        expect(move, "should return a Move") {
            MoveRequestHandler.handleMoveRequest(aMoveRequest)
        }

        verify { MoveService.getMoveByName(move.name) }
    }

    @Test
    fun `when handling a MovesRequest without filter`() {
        expect(allMoves, "should return Moves") {
            MoveRequestHandler.handleMovesRequest(aMovesRequest)
        }

        verify { MoveService.getAllMoves() }
    }

    @Test
    fun whenHandlingAMovesRequestWithFilter() {
        expect(filteredMoves, "should return filtered Moves") {
            MoveRequestHandler.handleMovesRequest(aFilterMovesRequest)
        }

        verify { MoveService.getMovesByFilter(aMoveFilter) }
    }
}