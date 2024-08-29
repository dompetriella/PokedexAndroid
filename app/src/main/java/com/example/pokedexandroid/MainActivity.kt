package com.example.pokedexandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
        containerColor = Color(0xFF141414),
        topBar = { AppBar() },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Center
            ) {
                Column {
                    PokedexSelectContainer()
                    PokedexSelectContainer()
                    PokedexSelectContainer()
                }

            }
        }
    )
}

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
                    color = Color(0xFFE7E7E7),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(alignment = TopCenter)

                )

            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color(0xFF141414)
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
                .background(Color(0xFF8F2D2D))
                .align(Alignment.BottomCenter)


        )
        // Original border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFE7E7E7))
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun PokedexSelectContainer() {

    val clicked: Boolean = false

    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(vertical = 16.dp)
            .offset(x = (-40).dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(width = 1.dp, color = Color(0xFFE7E7E7))

    ) {
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .padding(start = 32.dp)
                    .padding(vertical = 8.dp)
                    .padding(end = 16.dp)
                    .height(72.dp)
                    .width(72.dp)

                    .background(color = Color.Blue)

            )
            Box(
                modifier = Modifier

                    .padding(vertical = 8.dp)
                    .height(72.dp)
                    .fillMaxWidth()

                    .background(color = Color.Red)

            )
            {
                Text("Charizard", modifier = Modifier.align(Center))
            }
        }

    }
}