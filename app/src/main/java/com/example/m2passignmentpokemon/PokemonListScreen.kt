package com.example.m2passignmentpokemon;

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.m2passignmentpokemon.model.Data

import com.example.m2passignmentpokemon.viewmodel.PokemonViewModel;
import com.example.m2passignmentpokemon.viewmodel.SortOrder

@Composable
fun PokemonListScreen(navController: NavHostController) {
    val viewModel: PokemonViewModel = hiltViewModel()
    val state by viewModel.pokemonListState.collectAsState()

    Column {
        SearchBar(state.searchQuery, onSearchQueryChanged = viewModel::onSearchQueryChanged)
        SortOptions(state.sortOrder, onSortOrderChanged = viewModel::onSortOrderChanged)
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
                ) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        } else {
            PokemonCardList(state.pokemonCards, navController, viewModel::loadNextPage)
        }
    }
}

@Composable
fun SearchBar(query: String, onSearchQueryChanged: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = onSearchQueryChanged,
        label = { Text("Search Pokemon") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun SortOptions(sortOrder: SortOrder, onSortOrderChanged: (SortOrder) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = { onSortOrderChanged(SortOrder.BY_LEVEL) }) {
            Text("Sort by Level")
        }
        Button(onClick = { onSortOrderChanged(SortOrder.BY_HP) }) {
            Text("Sort by HP")
        }
    }
}

@Composable
fun PokemonCardList(pokemonCards: List<Data>, navController: NavHostController, loadNextPage: () -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(pokemonCards) {card ->
            PokemonCardItem(card, navController)
        }
        item {
            Button(onClick = loadNextPage,
                modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Text("Load More")
            }
        }
    }
}

@Composable
fun PokemonCardItem(card: Data, navController: NavHostController) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { navController.navigate("details/${card.id}") }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(data = card.images.small),
                contentDescription = card.name,
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = card.name, style = MaterialTheme.typography.headlineSmall)
                Text(text = "Types: ${card.types.joinToString(", ")}")
                Text(text = "Level: ${card.level}")
                Text(text = "HP: ${card.hp}")
            }
        }
    }
}