package com.gablalib.pokedexcore.controllers

import com.gablalib.pokedexcore.controllers.requests.MovesRequest
import com.gablalib.pokedexcore.controllers.requests.SimpleRequest
import com.gablalib.pokedexcore.services.requestHandlers.MoveRequestHandler
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import com.gablalib.pokedexcore.mocks.models.MoveMocks
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import kotlin.test.expect

class MoveControllerTest {
    companion object {
        private val move = MoveMocks.tackle()
        private val moves = arrayListOf(move)
        private val aMoveRequest = SimpleRequest(move.name)
        private val aMovesRequest = MovesRequest()

        @BeforeAll
        @JvmStatic
        fun init() {
            mockkObject(MoveRequestHandler)

            every { MoveRequestHandler.handleMoveRequest(aMoveRequest) } returns move
            every { MoveRequestHandler.handleMovesRequest(aMovesRequest) } returns moves
        }

        @AfterAll
        @JvmStatic
        fun exit() {
            unmockkAll()
        }
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