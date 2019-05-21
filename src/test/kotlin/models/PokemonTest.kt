package models

import com.gablalib.pokedexcore.models.pokemon.Pokemon
import com.gablalib.pokedexcore.models.type.Type
import org.junit.Test
import kotlin.test.expect


class PokemonTest {

    private val bulbasaur = Pokemon(
        "Bulbasaur",
        typing = arrayListOf(
            Type.GRASS,
            Type.POISON
        )
    )

    @Test fun isOfType() {
        expect(true, "when Bulbasaur is of type GRASS") { bulbasaur.isOfType(Type.GRASS) }
        expect(false, "when Bulbasaur is of type FIRE") { bulbasaur.isOfType(Type.FIRE) }
    }
}