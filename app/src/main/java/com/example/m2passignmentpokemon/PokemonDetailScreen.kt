package com.example.m2passignmentpokemon

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.m2passignmentpokemon.viewmodel.PokemonViewModel

@SuppressLint("RememberReturnType")
@Composable
fun PokemonDetailScreen(pokemonId: String) {

    val viewModel: PokemonViewModel = hiltViewModel()
    val pokemonCardFlow = remember { viewModel.getPokemonCardById(pokemonId) }

    val pokemonCardState by pokemonCardFlow.collectAsState(initial = null)

    Box(modifier = Modifier.fillMaxSize()) {
        if (pokemonCardState == null) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            val card = pokemonCardState!!
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = card.images.large,
                    ) ,
                    contentDescription = card.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Text(text = card.name, style = MaterialTheme.typography.headlineLarge)
                Text(text = "Types: ${card.types.joinToString(", ")}")
                Text(text = "Sub Types: ${card.subtypes.joinToString(", ")}")
                Text(text = "Level: ${card.level}")
                Text(text = "HP: ${card.hp}")
                Text(text = "Attacks: ${card.attacks.joinToString { it.name }}")
                Text(text = "Weakness: ${card.weaknesses?.joinToString { it.type }}")
                Text(text = "Abilities: ${card.abilities?.joinToString { it.name }}")
                Text(text = "Resistances: ${card.resistances?.joinToString { it.type }}")
            }
        }
    }
}