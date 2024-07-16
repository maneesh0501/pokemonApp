package com.example.m2passignmentpokemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavGraph(startDestination: String = "pokemon_list") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
//        composable("pokemon_list") {
//            PokemonListScreen { pokemon ->
//                navController.navigate("pokemon_detail/${pokemon.id}")
//            }
//        }
//        composable(
//            "pokemon_detail/{pokemonId}",
//            arguments = listOf(navArgument("pokemonId") { type = NavType.StringType})
//        ) { backStackEntry ->
//            val pokemonId = backStackEntry.arguments?.getString("pokemonId")
//            val pokemon = // Get Pokemon by ID
//                pokemon?.let {
//                    PokemonDetailScreen(pokemon = it)
//                }
//        }
    }
}