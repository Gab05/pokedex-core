package mocks.entities

import com.gablalib.pokedexcore.repositories.entities.SpriteEntity

class SpriteEntityMocks {
    companion object {
        fun spriteEntity(): SpriteEntity = SpriteEntity("pidgeot", ByteArray(0), ByteArray(1))
    }
}