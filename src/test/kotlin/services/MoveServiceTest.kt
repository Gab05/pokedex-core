package services

import com.gablalib.pokedexcore.factories.move.MoveFactory
import com.gablalib.pokedexcore.factories.move.MoveMongoFilterFactory
import com.gablalib.pokedexcore.filters.MoveFilter
import com.gablalib.pokedexcore.repositories.move.MoveMongoRepo
import com.gablalib.pokedexcore.services.MoveService
import com.gablalib.pokedexcore.services.exceptions.MoveNotFoundException
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import mocks.entities.MoveEntityMocks
import mocks.models.MoveMocks
import org.bson.conversions.Bson
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.litote.kmongo.EMPTY_BSON
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.expect

class MoveServiceTest {

    private val tackle = MoveMocks.tackle()
    private val agility = MoveMocks.agility()
    private val allMoves = arrayListOf(tackle, agility)
    private val filteredMoves = arrayListOf(tackle)

    private val tackleEntity = MoveEntityMocks.tackle()
    private val agilityEntity = MoveEntityMocks.agility()
    private val allEntities = arrayListOf(tackleEntity, agilityEntity)
    private val filteredEntities = arrayListOf(tackleEntity)

    private val moveFilter = MoveFilter()
    private val mongoFilter: Bson = EMPTY_BSON
    private val notAMoveName = "not_a_move"

    @Before
    fun init() {
        mockkObject(MoveMongoRepo)
        every { MoveMongoRepo.findAll() } returns allEntities
        every { MoveMongoRepo.findByName(tackle.name) } returns tackleEntity
        every { MoveMongoRepo.findByName(notAMoveName) } returns null
        every { MoveMongoRepo.findAllByFilter(mongoFilter) } returns filteredEntities

        mockkObject(MoveFactory)
        every { MoveFactory.create(tackleEntity) } returns tackle
        every { MoveFactory.createAll(allEntities) } returns allMoves
        every { MoveFactory.createAll(arrayListOf(tackleEntity)) } returns filteredMoves

        mockkObject(MoveMongoFilterFactory)
        every { MoveMongoFilterFactory.create(moveFilter) } returns mongoFilter
    }

    @After
    fun exit() {
        unmockkAll()
    }

    @Test
    fun `when getting all moves`() {
        expect(allMoves, "should return a Move list") {
            MoveService.getAllMoves()
        }

        verify { MoveMongoRepo.findAll() }
        verify { MoveFactory.createAll(allEntities) }
    }

    @Test
    fun `when getting a move by name`() {
        expect(tackle, "should return a Move") {
            MoveService.getMoveByName(tackle.name)
        }

        verify { MoveMongoRepo.findByName(tackle.name) }
        verify { MoveFactory.create(tackleEntity) }
    }

    @Test
    fun `when getting a move by name that does not exist`() {
        val e = assertFailsWith<MoveNotFoundException> {
            MoveService.getMoveByName(notAMoveName)
        }

        verify { MoveMongoRepo.findByName(notAMoveName) }
        assertEquals(e.name, notAMoveName)
    }

    @Test
    fun `when getting all moves with a filter`() {
        expect(filteredMoves, "should return filtered moves") {
            MoveService.getMovesByFilter(moveFilter)
        }

        verify { MoveMongoFilterFactory.create(moveFilter) }
        verify { MoveMongoRepo.findAllByFilter(mongoFilter) }
        verify { MoveFactory.createAll(filteredEntities) }
    }
}