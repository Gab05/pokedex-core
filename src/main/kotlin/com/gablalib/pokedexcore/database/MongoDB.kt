package com.gablalib.pokedexcore.database

import com.mongodb.ConnectionString
import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.*
import com.gablalib.pokedexcore.repositories.entities.MoveEntity
import com.gablalib.pokedexcore.repositories.entities.PokemonEntity

object MongoDB {

    private val client: MongoClient = KMongo.createClient(ConnectionString(
        "mongodb+srv://admin:420cherubi@pokecluster-hsbyc.mongodb.net/test?retryWrites=true"))

    fun getDB(): MongoDatabase {
        return client.getDatabase("pokedb")
    }
}