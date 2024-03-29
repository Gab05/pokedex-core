package com.gablalib.pokedexcore.services.requestHandlers

import com.gablalib.pokedexcore.controllers.requests.MovesRequest
import com.gablalib.pokedexcore.controllers.requests.SimpleRequest
import com.gablalib.pokedexcore.filters.MoveFilter
import com.gablalib.pokedexcore.mocks.models.MoveMocks
import com.gablalib.pokedexcore.services.MoveService
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import kotlin.test.expect

class MoveRequestHandlerTest {
    companion object {
        private val tackle = MoveMocks.tackle()
        private val agility = MoveMocks.agility()
        private val allMoves = arrayListOf(tackle, agility)
        private val filteredMoves = arrayListOf(tackle)

        private val nonNullFilter = MoveFilter()

        private val simpleRequest = SimpleRequest(tackle.name)
        private val movesRequest = MovesRequest(nonNullFilter)
        private val nullFilterRequest = MovesRequest()

        @BeforeAll
        @JvmStatic
        fun init() {
            mockkObject(MoveService)

            every { MoveService.getAllMoves() } returns allMoves
            every { MoveService.getMoveByName(simpleRequest.name) } returns tackle
            every { MoveService.getMovesByFilter(nonNullFilter) } returns filteredMoves
        }

        @AfterAll
        @JvmStatic
        fun exit() {
            unmockkAll()
        }
    }

    @Test
    fun `when handling a simple move request`() {
        expect(tackle, "should return a move") {
            MoveRequestHandler.handleMoveRequest(simpleRequest)
        }

        verify { MoveService.getMoveByName(simpleRequest.name) }
    }

    @Test
    fun `when handling a null moves request`() {
        expect(allMoves, "should return all moves") {
            MoveRequestHandler.handleMovesRequest(null)
        }

        verify { MoveService.getAllMoves() }
    }

    @Test
    fun `when handling a non-null moves request with a null filter`() {
        expect(allMoves, "should return Moves") {
            MoveRequestHandler.handleMovesRequest(nullFilterRequest)
        }

        verify { MoveService.getAllMoves() }
    }

    @Test
    fun `when handling a non-null moves request with a non-null filter`() {
        expect(filteredMoves, "should return filtered moves") {
            MoveRequestHandler.handleMovesRequest(movesRequest)
        }

        verify { MoveService.getMovesByFilter(nonNullFilter) }
    }
}