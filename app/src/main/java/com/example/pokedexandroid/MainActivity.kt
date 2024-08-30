package com.example.pokedexandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedexandroid.ui.theme.PokemonBlack
import com.example.pokedexandroid.ui.theme.PokemonRed
import com.example.pokedexandroid.ui.theme.PokemonWhite

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
                    .padding(innerPadding),
                contentAlignment = CenterStart
            ) {
                LazyColumn {
                    items(count = 20) { index ->
                        PokedexSelectContainer(index = index)
                    }
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
                    text = "IPSUM",
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

@Composable
fun PokedexSelectContainer(index: Int) {

    var isClicked by remember { mutableStateOf(false) }
    val formattedNumber = String.format("#%03d", index + 1)
    var contentSize = if (!isClicked) 50 else 100

    Box {
        Text(
            formattedNumber,
            color = PokemonWhite,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        )
    }
    OutlinedButton(
        onClick = { isClicked = !isClicked },
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = if (!isClicked) 0.dp else 32.dp)
            .fillMaxWidth(if (!isClicked) 0.8f else 1f),
        shape = RoundedCornerShape(2.dp),
        border = BorderStroke(width = 1.dp, color = PokemonWhite)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,


                ) {
                Box(
                    modifier = Modifier
                        .height(contentSize.dp)
                        .width(contentSize.dp)
                        .background(color = Color.Blue)
                )
                if (isClicked) Spacer(modifier = Modifier.height(12.dp))
                if (isClicked) Row(horizontalArrangement = Arrangement.Center) {
                    PokemonTypeDisplay()
                    Spacer(modifier = Modifier.width(8.dp))
                    PokemonTypeDisplay()

                }

            }

            Box(
                modifier = Modifier
                    .background(color = Color.Red)

            )

            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()

                ) {
                    Text(
                        "LOREM".uppercase(),
                        color = PokemonWhite,
                        fontFamily = FontFamily.Monospace,
                        fontSize = if (!isClicked) 16.sp else 22.sp,
                        fontWeight = FontWeight.Normal,
                    )

                    if (isClicked) Text(
                        "LOREM IPSUM LOROIs".uppercase(),
                        color = PokemonWhite,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Light,
                    )

                }

            }
        }

    }
}

@Composable
fun PokemonTypeDisplay() {
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
            "FIRE", color = PokemonWhite,
            fontSize = 8.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Light,
            modifier = Modifier.align(Center)
        )
    }

}