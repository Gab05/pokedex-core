package controllers

import com.gablalib.pokedexcore.controllers.MoveController
import com.gablalib.pokedexcore.controllers.requests.MovesRequest
import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.services.MoveService
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.expect

class MoveControllerTest {

    private val A_MOVE_NAME = "tackle"

    @Before
    fun init() {
        mockkObject(MoveService)

        every { MoveService.getAllMoves() } returns ArrayList()
        every { MoveService.getMoveByName(A_MOVE_NAME) } returns Move(A_MOVE_NAME)
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun whenGettingAllMoves() {
        expect(ArrayList(), "should return a list of moves") {
            MoveController.moves(MovesRequest(null))
        }

        verify { MoveService.getAllMoves() }
    }

    @Test
    fun whenGettingMoveByName() {
        expect(A_MOVE_NAME, "should return a Move with matching name") {
            MoveController.moveName(A_MOVE_NAME).name
        }

        verify { MoveService.getMoveByName(A_MOVE_NAME) }
    }
}