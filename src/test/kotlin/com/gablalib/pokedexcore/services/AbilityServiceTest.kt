package com.gablalib.pokedexcore.services

import com.gablalib.pokedexcore.factories.move.AbilityFactory
import com.gablalib.pokedexcore.factories.move.AbilityMongoFilterFactory
import com.gablalib.pokedexcore.filters.AbilityFilter
import com.gablalib.pokedexcore.mocks.entities.AbilityEntityMocks
import com.gablalib.pokedexcore.mocks.models.AbilityMocks
import com.gablalib.pokedexcore.repositories.ability.AbilityMongoRepo
import com.gablalib.pokedexcore.services.exceptions.AbilityNotFoundException
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.litote.kmongo.EMPTY_BSON
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.expect

class AbilityServiceTest {
    companion object {
        private val roughSkin = AbilityMocks.roughSkin()
        private val torrent = AbilityMocks.torrent()
        private val allAbilities = arrayListOf(roughSkin, torrent)
        private val filteredAbilities = arrayListOf(roughSkin)

        private val roughSkinEntity = AbilityEntityMocks.roughSkin()
        private val torrentEntity = AbilityEntityMocks.torrent()
        private val allEntities = arrayListOf(roughSkinEntity, torrentEntity)
        private val filteredEntities = arrayListOf(roughSkinEntity)

        private val abilityFilter = AbilityFilter()
        private val mongoFilter = EMPTY_BSON
        private val notAnAbilityName = "whatever"

        @BeforeAll
        @JvmStatic
        fun init() {
            mockkObject(AbilityMongoRepo)
            every { AbilityMongoRepo.findAll() } returns allEntities
            every { AbilityMongoRepo.findByName(roughSkin.name) } returns roughSkinEntity
            every { AbilityMongoRepo.findAllByFilter(mongoFilter) } returns filteredEntities

            mockkObject(AbilityFactory)
            every { AbilityFactory.create(roughSkinEntity) } returns roughSkin
            every { AbilityFactory.create(torrentEntity) } returns torrent
            every { AbilityFactory.createAll(allEntities) } returns allAbilities
            every { AbilityFactory.createAll(filteredEntities) } returns filteredAbilities

            mockkObject(AbilityMongoFilterFactory)
            every { AbilityMongoFilterFactory.create(abilityFilter) } returns mongoFilter
        }

        @AfterAll
        @JvmStatic
        fun exit() {
            unmockkAll()
        }
    }

    @Test
    fun `when getting all abilities`() {
        expect(allAbilities, "should return a list of all abilities") {
            AbilityService.getAllAbilities()
        }

        verify { AbilityMongoRepo.findAll() }
        verify { AbilityFactory.createAll(arrayListOf(roughSkinEntity, torrentEntity)) }
    }

    @Test
    fun `when getting an ability by name`() {
        expect(roughSkin, "should return an ability") {
            AbilityService.getAbilityByName(roughSkin.name)
        }

        verify { AbilityMongoRepo.findByName(roughSkin.name) }
        verify { AbilityFactory.create(roughSkinEntity) }
    }

    @Test
    fun `when getting a non-existing ability`() {
        val e = assertFailsWith<AbilityNotFoundException> {
            AbilityService.getAbilityByName(notAnAbilityName)
        }

        verify { AbilityMongoRepo.findByName(notAnAbilityName) }
        assertEquals(e.name, notAnAbilityName)
    }

    @Test
    fun `when getting abilities by filter`() {
        expect(filteredAbilities, "should return filtered abilities") {
            AbilityService.getAbilitiesByFilter(abilityFilter)
        }

        verify { AbilityMongoFilterFactory.create(abilityFilter) }
        verify { AbilityMongoRepo.findAllByFilter(mongoFilter) }
        verify { AbilityFactory.createAll(filteredEntities) }
    }
}