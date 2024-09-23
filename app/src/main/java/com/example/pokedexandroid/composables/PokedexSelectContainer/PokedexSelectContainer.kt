package com.example.pokedexandroid.composables.PokedexSelectContainer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseOutQuint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.pokedexandroid.api.Pokemon
import com.example.pokedexandroid.api.PokemonRepository
import com.example.pokedexandroid.api.PokemonViewModel
import com.example.pokedexandroid.api.pokemonApiService
import com.example.pokedexandroid.composables.PokedexSelectContainer.composables.PokedexPokemonTitle
import com.example.pokedexandroid.composables.PokedexSelectContainer.composables.PokemonTypeDisplay
import com.example.pokedexandroid.ui.theme.PokemonBlack
import com.example.pokedexandroid.ui.theme.PokemonRed
import com.example.pokedexandroid.ui.theme.PokemonWhite
import kotlinx.coroutines.launch

@Composable
fun PokedexSelectContainer(pokemon: Pokemon, viewModel: PokemonViewModel) {

    val pokemonRepository = PokemonRepository(pokemonApiService = pokemonApiService)
    val coroutineScope = rememberCoroutineScope()


    var isSelected by remember { mutableStateOf(false) }
    val formattedNumber = String.format("#%03d", pokemon.id)
    val contentSize = if (!isSelected) 60 else 100
    val numberOffsetX =
        animateDpAsState(targetValue = if (!isSelected) 30.dp else 71.dp, label = "")
    val numberOffsetY = animateDpAsState(
        targetValue = if (!isSelected) 9.dp else 23.dp,
        label = "",
        animationSpec = tween(durationMillis = 1000, easing = EaseOutQuint)
    )
    val horizontalPadding = animateDpAsState(
        targetValue = if (!isSelected) 0.dp else 32.dp,
        label = "",
        animationSpec = tween(durationMillis = 1000, easing = EaseOutQuint)
    )
    val redOffset = animateDpAsState(
        if (isSelected) (-8).dp else 0.dp,
        label = "",
        animationSpec = tween(durationMillis = 600, delayMillis = 400)
    )
    val numberFontSize = if (!isSelected) 16 else 40

    Column {
        Text(
            formattedNumber,
            color = PokemonWhite,
            fontSize = numberFontSize.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .offset(x = numberOffsetX.value, y = numberOffsetY.value)
                .zIndex(zIndex = 100.0F)
        )
        Box(modifier = Modifier.padding(vertical = 8.dp)) {
            if (isSelected) {
                Box(
                    modifier = Modifier

                        .matchParentSize()

                        .padding(vertical = 4.dp, horizontal = if (!isSelected) 0.dp else 32.dp)
                        .offset(x = redOffset.value, y = redOffset.value)
                        .border(BorderStroke(1.dp, PokemonRed), shape = RoundedCornerShape(2.dp))
                )
            }


            OutlinedButton(
                onClick = {
                    isSelected = !isSelected
                    // Launch the coroutine when the button is clicked
                    coroutineScope.launch {
                        pokemonRepository.getPokemonData(pokemon, viewModel)
                    }
                },
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = horizontalPadding.value)
                    .offset(x = if (!isSelected) (-24).dp else 0.dp)

                    .fillMaxWidth(if (!isSelected) 0.85f else 1f),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = PokemonBlack),
                shape = RoundedCornerShape(2.dp),
                border = BorderStroke(width = 1.dp, color = PokemonWhite)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
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
                                modifier = Modifier
                                    .fillMaxSize(),
                                colorFilter = if (!isSelected) ColorFilter.tint(
                                    color = Color.White,
                                    blendMode = BlendMode.SrcIn
                                )
                                else {
                                    null
                                }

                            )
                        }

                        AnimatedVisibility(isSelected) {
                            Spacer(
                                modifier = Modifier
                                    .height(12.dp)
                            )
                        }
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

    }
}