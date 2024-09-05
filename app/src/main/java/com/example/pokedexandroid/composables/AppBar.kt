package com.example.pokedexandroid.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedexandroid.ui.theme.PokemonBlack
import com.example.pokedexandroid.ui.theme.PokemonRed
import com.example.pokedexandroid.ui.theme.PokemonWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "POKEDEX",
                    color = PokemonWhite,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(alignment = TopCenter)

                )

            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = PokemonBlack
            ),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )
        // Border at the bottom
        Box(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(PokemonRed)
                .align(Alignment.BottomCenter)


        )
        // Original border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(PokemonWhite)
                .align(Alignment.BottomCenter)
        )
    }
}