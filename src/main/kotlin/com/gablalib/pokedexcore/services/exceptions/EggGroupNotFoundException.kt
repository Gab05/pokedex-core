package com.gablalib.pokedexcore.services.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpStatusCodeException

data class EggGroupNotFoundException(val name: String):
    HttpStatusCodeException(HttpStatus.NOT_FOUND, "Requested egg group '%s' was not found.".format(name))