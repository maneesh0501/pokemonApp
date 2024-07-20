package com.example.m2passignmentpokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.m2passignmentpokemon.ui.theme.M2PAssignmentPokemonTheme
import com.example.m2passignmentpokemon.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val pokemonViewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            M2PAssignmentPokemonTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "pokemon_list") {
                        composable("pokemon_list") {
                            PokemonListScreen(navController)
                        }
                        composable(
                            "details/{pokemonId}",
                            arguments = listOf(navArgument("pokemonId") { type = NavType.StringType })
                        ) {navBackStackEntry ->
                            val pokemonId = navBackStackEntry.arguments?.getString("pokemonId")
                            if (pokemonId != null) {
                                PokemonDetailScreen(pokemonId = pokemonId)
                            }
                        }
                    }
                }
            }
        }
    }
}