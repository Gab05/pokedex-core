package com.gablalib.pokedexcore.services.requestHandlers

import com.gablalib.pokedexcore.controllers.requests.AbilitiesRequest
import com.gablalib.pokedexcore.controllers.requests.SimpleRequest
import com.gablalib.pokedexcore.filters.AbilityFilter
import com.gablalib.pokedexcore.services.AbilityService
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import com.gablalib.pokedexcore.mocks.models.AbilityMocks
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import kotlin.test.expect

class AbilityRequestHandlerTest {
    companion object {
        private val roughSkin = AbilityMocks.roughSkin()
        private val torrent = AbilityMocks.torrent()
        private val allAbilities = arrayListOf(roughSkin, torrent)
        private val filteredAbilities = arrayListOf(torrent)

        private val nonNullFilter = AbilityFilter()

        private val simpleRequest = SimpleRequest(torrent.name)
        private val nullFilterRequest = AbilitiesRequest()
        private val abilitiesRequest = AbilitiesRequest(nonNullFilter)

        @BeforeAll
        @JvmStatic
        fun init() {
            mockkObject(AbilityService)

            every { AbilityService.getAllAbilities() } returns allAbilities
            every { AbilityService.getAbilityByName(torrent.name) } returns torrent
            every { AbilityService.getAbilitiesByFilter(nonNullFilter) } returns filteredAbilities
        }

        @AfterAll
        @JvmStatic
        fun exit() {
            unmockkAll()
        }
    }

    @Test
    fun `when handling a simple ability request`() {
        expect(torrent, "should return an ability") {
            AbilityRequestHandler.handleAbilityRequest(simpleRequest)
        }

        verify { AbilityService.getAbilityByName(simpleRequest.name) }
    }

    @Test
    fun `when handling a null abilities request`() {
        expect(allAbilities, "should return all abilities") {
            AbilityRequestHandler.handleAbilitiesRequest(null)
        }

        verify { AbilityService.getAllAbilities() }
    }

    @Test
    fun `when handling a non-null abilities request with a null filter`() {
        expect(allAbilities, "should return all abilities") {
            AbilityRequestHandler.handleAbilitiesRequest(nullFilterRequest)
        }

        verify { AbilityService.getAllAbilities() }
    }

    @Test
    fun `when handling a non-null abilities request with a non-null filter`() {
        expect(filteredAbilities, "should return filtered Moves") {
            AbilityRequestHandler.handleAbilitiesRequest(abilitiesRequest)
        }

        verify { AbilityService.getAbilitiesByFilter(nonNullFilter) }
    }
}