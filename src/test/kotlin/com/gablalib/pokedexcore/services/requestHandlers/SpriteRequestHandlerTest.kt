package com.gablalib.pokedexcore.services.requestHandlers

import com.gablalib.pokedexcore.controllers.requests.SimpleRequest
import com.gablalib.pokedexcore.services.SpriteService
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import kotlin.test.expect

class SpriteRequestHandlerTest {
    companion object {
        private val simpleRequest = SimpleRequest("garchomp")
        private val normalSprite = ByteArray(0)
        private val shinySprite = ByteArray(1)

        @BeforeAll
        @JvmStatic
        fun init() {
            mockkObject(SpriteService)

            every { SpriteService.getNormalPokemonSprite(simpleRequest.name) } returns normalSprite
            every { SpriteService.getShinyPokemonSprite(simpleRequest.name) } returns shinySprite
        }

        @AfterAll
        @JvmStatic
        fun exit() {
            unmockkAll()
        }
    }

    @Test
    fun `when handling a simple normal sprite request`() {
        expect(normalSprite, "should return a normal sprite") {
            SpriteRequestHandler.handleNormalPokemonRequest(simpleRequest)
        }

        verify { SpriteService.getNormalPokemonSprite(simpleRequest.name) }
    }

    @Test
    fun `when handling a simple shiny sprite request`() {
        expect(shinySprite, "should return a shiny sprite") {
            SpriteRequestHandler.handleShinyPokemonRequest(simpleRequest)
        }

        verify { SpriteService.getShinyPokemonSprite(simpleRequest.name) }
    }
}