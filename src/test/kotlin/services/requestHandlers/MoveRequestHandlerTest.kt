package services.requestHandlers

import com.gablalib.pokedexcore.controllers.requests.MoveRequest
import com.gablalib.pokedexcore.controllers.requests.MovesRequest
import com.gablalib.pokedexcore.filters.MoveFilter
import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.services.MoveService
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

class MoveRequestHandlerTest {

    private val aMoveName = "double_team"
    private val aMoveRequest = MoveRequest(aMoveName)
    private val aMoveFilter = MoveFilter(arrayListOf(aMoveName))
    private val aMovesRequest = MovesRequest(null)
    private val aFilterMovesRequest = MovesRequest(aMoveFilter)
    private val move = Move(aMoveName)
    private val moves = arrayListOf(Move(aMoveName))
    private val filteredMoves = ArrayList<Move>()
    private val aMoveResponse = MoveResponse(move)
    private val aMovesResponse = MovesResponse(moves)
    private val aFilteredMovesResponse = MovesResponse(filteredMoves)

    @Before
    fun init() {
        mockkObject(MoveService)

        every { MoveService.getAllMoves() } returns moves
        every { MoveService.getMoveByName(aMoveName) } returns move
        every { MoveService.getMovesByFilter(aMoveFilter) } returns filteredMoves
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun whenHandlingAMoveRequest() {
        expect(aMoveResponse, "should return a MoveResponse") {
            MoveRequestHandler.handleMoveRequest(aMoveRequest)
        }

        verify { MoveService.getMoveByName(aMoveName) }
    }

    @Test
    fun whenHandlingAMovesRequestWithoutFilter() {
        expect(aMovesResponse, "should return a MovesResponse") {
            MoveRequestHandler.handleMovesRequest(aMovesRequest)
        }

        verify { MoveService.getAllMoves() }
    }

    @Test
    fun whenHandlingAMovesRequestWithFilter() {
        expect(aFilteredMovesResponse, "should return a filtered MovesResponse") {
            MoveRequestHandler.handleMovesRequest(aFilterMovesRequest)
        }

        verify { MoveService.getMovesByFilter(aMoveFilter) }
    }
}