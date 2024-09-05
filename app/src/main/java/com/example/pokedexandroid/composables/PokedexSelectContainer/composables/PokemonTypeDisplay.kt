package com.example.pokedexandroid.composables.PokedexSelectContainer.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedexandroid.ui.theme.PokemonWhite

@Composable
fun PokemonTypeDisplay(pokemonType: String) {
    Box(
        modifier = Modifier
            .border(
                border = BorderStroke(
                    width = 0.5.dp,
                    color = PokemonWhite

                )
            )
            .width(60.dp)
            .height(20.dp)
    ) {
        Text(
            pokemonType.uppercase(), color = PokemonWhite,
            fontSize = 8.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Light,
            modifier = Modifier.align(Center)
        )
    }

}