package com.example.pokedexandroid.composables.PokedexSelectContainer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokedexandroid.api.Pokemon
import com.example.pokedexandroid.composables.PokedexSelectContainer.composables.PokedexPokemonTitle
import com.example.pokedexandroid.composables.PokedexSelectContainer.composables.PokemonTypeDisplay
import com.example.pokedexandroid.ui.theme.PokemonWhite

@Composable
fun PokedexSelectContainer(pokemon: Pokemon) {

    var isSelected by remember { mutableStateOf(false) }
    val formattedNumber = String.format("#%03d", pokemon.id)
    val contentSize = if (!isSelected) 50 else 100
    val numberOffsetX = if (!isSelected) 30 else 71
    val numberOffsetY = if (!isSelected) 8 else 15
    val numberFontSize = if (!isSelected) 16 else 40



    Box(modifier = Modifier.padding(top = 8.dp)) {
        Text(
            formattedNumber,
            color = PokemonWhite,
            fontSize = numberFontSize.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.offset(numberOffsetX.dp, numberOffsetY.dp)
        )
    }
    OutlinedButton(
        onClick = { isSelected = !isSelected },
        modifier = Modifier

            .padding(vertical = 4.dp, horizontal = if (!isSelected) 0.dp else 32.dp)
            .offset(x = if (!isSelected) (-24).dp else 0.dp)
            .fillMaxWidth(if (!isSelected) 0.85f else 1f),
        shape = RoundedCornerShape(2.dp),
        border = BorderStroke(width = 1.dp, color = PokemonWhite)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,


                ) {
                Box(
                    modifier = Modifier

                        .padding(start = if (!isSelected) 24.dp else 0.dp)
                        .height(contentSize.dp)
                        .width(contentSize.dp)
                        .padding(8.dp)


                ) {
                    AsyncImage(
                        model = pokemon.imageUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        colorFilter = if (!isSelected) ColorFilter.tint(
                            color = Color.White, // The color you want the silhouette to be (in this case, white)
                            blendMode = BlendMode.SrcIn // Apply the white tint over the image
                        )
                        else {
                            null
                        }

                    )
                }

                if (isSelected) Spacer(modifier = Modifier.height(12.dp))
                if (isSelected) Row(horizontalArrangement = Arrangement.Center) {
                    PokemonTypeDisplay(pokemonType = pokemon.mainType)
                    Spacer(modifier = Modifier.width(8.dp))
                    if (pokemon.secondaryType != null) PokemonTypeDisplay(pokemonType = pokemon.secondaryType)
                }

            }
            PokedexPokemonTitle(isSelected, pokemon)
        }
    }
}