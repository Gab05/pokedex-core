# pokedex-core [![Build Status](https://travis-ci.org/Gab05/pokedex-core.svg?branch=master)](https://travis-ci.org/Gab05/pokedex-core)

This project is a pokedex API. It serves information about requested Pokémon and moves. Pokémon data is about the 7th generation of the main game series and has been fetched from serebii.net, a trustworthy source about Pokémon.

The application is deployed in pre-prod at https://gablalib-pokedex-core.herokuapp.com/. Performances are not optimal.

## Available routes

### Pokémon collection

 **GET** `/pokemons/{name}`
 Returns a single Pokémon if the route parameter `name` matches an existing Pokémon name. A Pokémon's `name` can be referred to as its id since they are unique, and have the following format:
 - Lowercase, ie `squirtle`
 - Spaces or symbols replaced by underscores [`tapu_koko`, `type_null`]
 - Alternate forms have distinguishable names, such as [`minior_core`, `minior_meteorite`], [`oricorio_baile`, `oricorio_pompom`, `oricorio_sensu`, `oricorio_pau`]
 - Mega evolutions have the prefix `mega_`, like `mega_kangaskhan`or `mega_mewtwo_x`
 - Alolan Pokémons have the suffix `_alola`, for instance `ninetales_alola`
 
---
 
 **GET** `/pokemons`
 Returns a list of every Pokémon.\
 **Request body** (optional):
 ```json
 {
   "filter": {
     "names": ["deoxys_attack", "deoxys_speed"],
     "nationalNumber": 386
   }
 }
 ```
 Every filter field is optional. Prioritizes topmost. Filter applies logical `AND` to fields.

---

 ### Move collection
 
 
 **GET** `/moves/{name}`
 Returns a single move if the route parameter `name` matches an existing move name.
 A move's `name` can be used as an id for they are unique. Same format standards as Pokémon names.

---
 
 **GET** `/moves`
 Returns a list of every move.\
 **Request body** (optional):
  ```json
  {
    "filter": {
      "names": ["splash", "tackle"]
    }
  }
  ```
  Field is optional.

 ## Objects structure
 
 ### Pokémon
 ### Moves
 TODO!
 
 
