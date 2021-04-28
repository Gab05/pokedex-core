package com.gablalib.pokedexcore.factories

import com.gablalib.pokedexcore.factories.move.MoveFactory
import com.gablalib.pokedexcore.models.move.Move
import com.gablalib.pokedexcore.models.move.MoveCategory
import com.gablalib.pokedexcore.models.type.Type
import com.gablalib.pokedexcore.repositories.entities.MoveEntity
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MoveFactoryTest {

    private val A_MOVE_NAME = "dragon_claw"
    private val A_MOVE_TYPE = "dragon"
    private val A_MOVE_ENTITY = MoveEntity(
        name = A_MOVE_NAME,
        type = A_MOVE_TYPE,
        category = "OTHER",
        pp = "15",
        power = "80",
        accuracy = "100",
        battleDescription = "Attacks target.",
        battleEffect = "No effect.",
        battleEffectRate = "--")
    private val MOVES_ENTITY = arrayListOf(A_MOVE_ENTITY, A_MOVE_ENTITY)

    @Test
    fun `when creating entities_then create is called on every entity`() {
        mockkObject(MoveFactory)

        MoveFactory.createAll(MOVES_ENTITY)
        verify(exactly = 2) { MoveFactory.create(A_MOVE_ENTITY) }

        unmockkObject(MoveFactory)
    }

    @Test
    fun `when creating an entity_then correct move is created`() {
        val expected = Move(A_MOVE_NAME, Type.valueOf(A_MOVE_TYPE.toUpperCase()), PP=15, power=80, accuracy = 100,
            category = MoveCategory.OTHER, battleDescription = "Attacks target.", battleEffect = "No effect.",
            battleEffectRate = "--")
        val actual = MoveFactory.create(A_MOVE_ENTITY)

        assertEquals(expected.name, actual.name)
        assertEquals(expected.type, actual.type)
        assertEquals(expected.category, actual.category)
        assertEquals(expected.PP, actual.PP)
        assertEquals(expected.power, actual.power)
        assertEquals(expected.accuracy, actual.accuracy)
        assertEquals(expected.battleDescription, actual.battleDescription)
        assertEquals(expected.battleEffect, actual.battleEffect)
        assertEquals(expected.battleEffectRate, actual.battleEffectRate)
    }
}