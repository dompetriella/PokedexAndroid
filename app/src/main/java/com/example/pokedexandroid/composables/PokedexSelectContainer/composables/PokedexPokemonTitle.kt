package com.example.pokedexandroid.composables.PokedexSelectContainer.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pokedexandroid.api.Pokemon
import com.example.pokedexandroid.ui.theme.PokemonWhite

@Composable
fun PokedexPokemonTitle(isSelected: Boolean, pokemon: Pokemon) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()

    ) {
        Text(
            pokemon.name.uppercase(),
            color = PokemonWhite,
            fontFamily = FontFamily.Monospace,
            fontSize = if (!isSelected) 16.sp else 22.sp,
            fontWeight = FontWeight.Normal,

            )

        if (isSelected) Text(
            pokemon.subtitle.uppercase(),
            color = PokemonWhite,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Light,
        )


    }
}