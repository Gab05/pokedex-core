package com.gablalib.pokedexcore.database

import com.mongodb.ConnectionString
import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.*

object MongoDB {

    // TODO: Store this in config file with readonly access user
    private val client: MongoClient = KMongo.createClient(ConnectionString(
        "mongodb+srv://trainer:x@pokecluster-hsbyc.mongodb.net/test?retryWrites=true"))

    fun getDB(): MongoDatabase {
        return client.getDatabase("pokedb")
    }
}