package com.example.pokedexandroid.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemonList(): List<Pokemon>
}


object RetrofitInstance {
    val api: PokemonApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon?limit=250") // Replace with your API's base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApiService::class.java)
    }
}
