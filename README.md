# pokedex-core

This project is a pokedex API. It serves information about requested Pokémon and moves. Pokémon data is about the 7th generation of the main game series and has been fetched from serebii.net, a trustworthy source about Pokémon.

This application's stable version is deployed to https://gablalib-pokedex-core.herokuapp.com/

## Available routes

### Pokémons

**GET** `/pokemons`
Returns a list of every Pokémon and their currently registered characteristics.


**GET** `/pokemons/{name}`
Returns a single Pokémon if the route parameter `name` matches an existing Pokémon name. A Pokémon's `name` can be referred to as its id since they are unique, and have the following format:
 - Pokémon names are lowercase, ie `squirtle`
 - Pokémon names with spaces or symbols have been replaced with underscores, as in `tapu_koko`and `type_null`
 - Pokémon with alternate forms are considered as different Pokémons and have distinguishable names, such as `minior_core` and `minior_meteorite`, or `oricorio_baile`, `oricorio_pompom`, `oricorio_sensu` and `oricorio_pau`
 - Mega evolutions have the prefix `mega_`, like `mega_kangaskhan`or `mega_mewtwo_x`
 - Alolan Pokémons have the suffix `_alola`, for instance `ninetales_alola`
 
 
 **GET** `/pokemons/number/{nationalNumber}`
 Returns a list of Pokémon with given National Number. Currently valid national numbers are between 1 and 807 inclusively. A list is returned, because multiple Pokémon can have the same number, such as mega evolutions and alola forms.
 
 ### Moves
 
 **GET** `/moves`
 Returns a list of every move and their currently registered characteristics.
 
 **GET** `/moves/{name}`
 Returns a single move if the route parameter `name` matches an existing move name. A move's `name` can be used as an id for they are unique, and have the same format standards as Pokémon names.
 
 
 ## Objects structure
 
 ### Pokémon
 ### Moves
 TODO!
 
 
