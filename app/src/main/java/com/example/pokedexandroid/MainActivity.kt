package com.example.pokedexandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedexandroid.api.Pokemon
import com.example.pokedexandroid.composables.AppBar
import com.example.pokedexandroid.composables.PokedexSelectContainer.PokedexSelectContainer
import com.example.pokedexandroid.ui.theme.PokemonBlack

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PokemonBlack,
        topBar = { AppBar() },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
                    .padding(innerPadding),
                contentAlignment = CenterStart
            ) {
                LazyColumn {
                    items(count = 20) { index ->
                        PokedexSelectContainer(
                            pokemon = Pokemon(
                                id = 1,
                                name = "Charizard",
                                subtitle = "The Flame Pokemon",
                                mainType = "Fire",
                                secondaryType = "Flying",
                                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/132.png"

                            )
                        )
                    }
                }

            }
        }
    )
}




