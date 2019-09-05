package com.gablalib.pokedexcore.services.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpStatusCodeException

data class AbilityNotFoundException(val name: String):
    HttpStatusCodeException(HttpStatus.NOT_FOUND, "Requested ability '%s' was not found.".format(name))