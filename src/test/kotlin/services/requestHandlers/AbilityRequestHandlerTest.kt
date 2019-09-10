package services.requestHandlers

import com.gablalib.pokedexcore.controllers.requests.AbilitiesRequest
import com.gablalib.pokedexcore.controllers.requests.SimpleRequest
import com.gablalib.pokedexcore.filters.AbilityFilter
import com.gablalib.pokedexcore.services.AbilityService
import com.gablalib.pokedexcore.services.requestHandlers.AbilityRequestHandler
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import mocks.models.AbilityMocks
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.expect

class AbilityRequestHandlerTest {

    private val roughSkin = AbilityMocks.roughSkin()
    private val torrent = AbilityMocks.torrent()
    private val allAbilities = arrayListOf(roughSkin, torrent)
    private val filteredAbilities = arrayListOf(torrent)

    private val nonNullFilter = AbilityFilter()

    private val simpleRequest = SimpleRequest(torrent.name)
    private val nullFilterRequest = AbilitiesRequest()
    private val abilitiesRequest = AbilitiesRequest(nonNullFilter)

    @Before
    fun init() {
        mockkObject(AbilityService)

        every { AbilityService.getAllAbilities() } returns allAbilities
        every { AbilityService.getAbilityByName(torrent.name) } returns torrent
        every { AbilityService.getAbilitiesByFilter(nonNullFilter) } returns filteredAbilities
    }

    @After
    fun exit() {
        unmockkAll()
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