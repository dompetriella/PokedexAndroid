package com.example.pokedexandroid.api

import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://pokeapi.co/api/v2/")
    .addConverterFactory(GsonConverterFactory.create())  // Assuming you're working with raw JSON strings
    .build()

val pokemonApiService: PokemonApiService = retrofit.create(PokemonApiService::class.java)

interface PokemonApiService {
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): ResponseBody

    @GET("pokemon")
    suspend fun getPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int = 0
    ): ResponseBody

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpeciesById(@Path("id") id: Int): ResponseBody
}






