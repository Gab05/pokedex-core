package com.gablalib.pokedexcore.repositories.pokemon

import com.gablalib.pokedexcore.repositories.MongoRepo
import com.gablalib.pokedexcore.repositories.entities.SpriteEntity
import org.bson.conversions.Bson
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

object PokemonSpritesMongoRepo: MongoRepo<SpriteEntity>() {
    private const val COLLECTION_NAME = "sprites"

    override val collection = this.db.getCollection<SpriteEntity>(COLLECTION_NAME)

    override fun findAll(): Collection<SpriteEntity> = collection.find().filterNotNull()

    override fun findByName(name: String): SpriteEntity? = collection.findOne(SpriteEntity::name eq name)

    override fun findAllByFilter(mongoFilter: Bson): Collection<SpriteEntity> = collection.find(mongoFilter).toList()
}