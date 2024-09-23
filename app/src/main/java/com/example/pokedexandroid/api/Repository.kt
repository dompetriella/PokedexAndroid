package com.example.pokedexandroid.api

import org.json.JSONObject

class PokemonRepository(private val pokemonApiService: PokemonApiService) {

    suspend fun getPokemon(limit: Int): List<Pokemon> {
        var rawJson = pokemonApiService.getPokemon(limit).string()
        val jsonObject = JSONObject(rawJson)

        val resultsArray = jsonObject.getJSONArray("results")

        val pokemonList: MutableList<Pokemon> = mutableListOf<Pokemon>()
        for (i in 0 until resultsArray.length()) {
            val pokemonObject = resultsArray.getJSONObject(i)

            // Get the name and URL of the Pokémon
            val name = pokemonObject.getString("name")

            var pokemon: Pokemon = Pokemon(
                id = i + 1,
                name = name.uppercase(),
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${i + 1}.png"
            )
            pokemonList.add(pokemon)

        }
        return pokemonList

    }

    suspend fun getPokemonData(pokemon: Pokemon, viewModel: PokemonViewModel) {

        if (pokemon.mainType != "") {
            return
        }

        var rawJson =
            com.example.pokedexandroid.api.pokemonApiService.getPokemonById(pokemon.id).string()
        val jsonObject = JSONObject(rawJson)
        // Extract the "types" array from the JSON
        val typesArray = jsonObject.getJSONArray("types")


        var mainType = ""
        var secondaryType: String? = null

        for (i in 0 until typesArray.length()) {
            val typeName = typesArray.getJSONObject(i)
                .getJSONObject("type")
                .getString("name")

            if (i == 0) {
                mainType = typeName
            } else if (i == 1) {
                secondaryType = typeName
            }
        }

        val subtitle = ""
        var updatedPokemon: Pokemon = Pokemon(
            pokemon.id,
            pokemon.name,
            subtitle,
            mainType,
            secondaryType,
            pokemon.imageUrl
        )
        viewModel.updatePokemonState(updatedPokemon, viewModel)
    }
}

