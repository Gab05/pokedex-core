package services

import com.gablalib.pokedexcore.factories.move.MoveFactory
import com.gablalib.pokedexcore.factories.move.MoveMongoFilterFactory
import com.gablalib.pokedexcore.filters.MoveFilter
import com.gablalib.pokedexcore.repositories.entities.MoveEntity
import com.gablalib.pokedexcore.repositories.move.MoveMongoRepo
import com.gablalib.pokedexcore.services.MoveService
import com.gablalib.pokedexcore.services.exceptions.MoveNotFoundException
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import mocks.MoveEntityMocks
import mocks.MoveMocks
import org.bson.conversions.Bson
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.litote.kmongo.EMPTY_BSON
import org.litote.kmongo.`in` as inList
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.expect

class MoveServiceTest {

    private val tackle = MoveMocks.tackle()
    private val tackleEntity = MoveEntityMocks.tackle()
    private val agility = MoveMocks.agility()
    private val agilityEntity = MoveEntityMocks.agility()
    private val allMoves = arrayListOf(tackle, agility)
    private val allEntities = arrayListOf(tackleEntity, agilityEntity)
    private val aNotFoundMoveName = "not_a_move"
    private val tackleNameFilter = MoveFilter(arrayListOf(tackle.name))
    private val tackleNameMongoFilter: Bson = MoveEntity::name inList arrayListOf(tackle.name)

    @Before
    fun init() {
        mockkObject(MoveMongoRepo)
        every { MoveMongoRepo.findAll() } returns allEntities
        every { MoveMongoRepo.findByName(tackle.name) } returns tackleEntity
        every { MoveMongoRepo.findByName(agility.name) } returns agilityEntity
        every { MoveMongoRepo.findByName(aNotFoundMoveName) } returns null
        every { MoveMongoRepo.findAllByFilter(tackleNameMongoFilter) } returns arrayListOf(tackleEntity)

        mockkObject(MoveFactory)
        every { MoveFactory.create(tackleEntity) } returns tackle
        every { MoveFactory.create(agilityEntity) } returns agility
        every { MoveFactory.createAll(allEntities) } returns allMoves
        every { MoveFactory.createAll(arrayListOf(tackleEntity)) } returns arrayListOf(tackle)

        mockkObject(MoveMongoFilterFactory)
        every { MoveMongoFilterFactory.create(tackleNameFilter) } returns tackleNameMongoFilter
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
            MoveService.getMoveByName(aNotFoundMoveName)
        }

        verify { MoveMongoRepo.findByName(aNotFoundMoveName) }
        assertEquals(e.name, aNotFoundMoveName)
    }

    @Test
    fun `when getting all moves with a filter`() {
        expect(arrayListOf(tackle), "should apply filter and return some moves") {
            MoveService.getMovesByFilter(tackleNameFilter)
        }

        verify { MoveMongoFilterFactory.create(tackleNameFilter) }
        verify { MoveMongoRepo.findAllByFilter(tackleNameMongoFilter) }
        verify { MoveFactory.createAll(arrayListOf(tackleEntity)) }
    }
}