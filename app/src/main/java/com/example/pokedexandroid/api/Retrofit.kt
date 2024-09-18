package com.example.pokedexandroid.api

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): ResponseBody
}

class PokemonRepository(private val pokemonApiService: PokemonApiService) {

    suspend fun getPokemonById(id: Int): Pokemon {
        var rawJson = pokemonApiService.getPokemonById(id).string()
        val jsonObject = JSONObject(rawJson)
        
        val name = jsonObject.getString("name")
        val id = jsonObject.getInt("id")
        val imageUrl = jsonObject.getJSONObject("sprites").getString("front_default")
        val subtitle = ""
        val mainType = ""
        val secondaryType = null
        return Pokemon(id, name, subtitle, mainType, secondaryType, imageUrl)
    }
}


