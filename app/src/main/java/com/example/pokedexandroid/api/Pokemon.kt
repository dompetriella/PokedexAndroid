package com.example.pokedexandroid.api

data class Pokemon(
    val id: Int,
    val name: String,
    val subtitle: String = "",
    val mainType: String = "",
    val secondaryType: String? = "",
    val imageUrl: String,
)