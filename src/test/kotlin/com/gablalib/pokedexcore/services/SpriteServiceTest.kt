package com.gablalib.pokedexcore.services

import com.gablalib.pokedexcore.repositories.pokemon.PokemonSpritesMongoRepo
import com.gablalib.pokedexcore.services.exceptions.PokemonNotFoundException
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import com.gablalib.pokedexcore.mocks.entities.SpriteEntityMocks
import com.gablalib.pokedexcore.mocks.models.PokemonMocks
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.expect

class SpriteServiceTest {

    private val garchomp = PokemonMocks.garchomp()
    private val spriteEntity = SpriteEntityMocks.spriteEntity()
    private val notAPokemonName = "wat"

    @Before
    fun init() {
        mockkObject(PokemonSpritesMongoRepo)
        every { PokemonSpritesMongoRepo.findByName(garchomp.name) } returns spriteEntity
        every { PokemonSpritesMongoRepo.findByName(notAPokemonName) } returns null
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun `when getting a normal pokemon sprite`() {
        expect(spriteEntity.normal, "should return a normal sprite") {
            SpriteService.getNormalPokemonSprite(garchomp.name)
        }

        verify { PokemonSpritesMongoRepo.findByName(garchomp.name) }
    }

    @Test
    fun `when getting a shiny pokemon sprite`() {
        expect(spriteEntity.shiny, "should return a shiny sprite") {
            SpriteService.getShinyPokemonSprite(garchomp.name)
        }

        verify { PokemonSpritesMongoRepo.findByName(garchomp.name) }
    }

    @Test
    fun `when getting a non-existing pokemon sprite`() {
        val e = assertFailsWith<PokemonNotFoundException> {
            SpriteService.getNormalPokemonSprite(notAPokemonName)
        }

        verify { PokemonSpritesMongoRepo.findByName(notAPokemonName) }
        assertEquals(e.name, notAPokemonName)
    }
}