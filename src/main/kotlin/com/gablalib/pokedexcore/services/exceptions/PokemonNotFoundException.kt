package com.gablalib.pokedexcore.services.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpStatusCodeException

data class PokemonNotFoundException(val name: String):
    HttpStatusCodeException(HttpStatus.NOT_FOUND, "Requested pokemon '%s' was not found.".format(name))