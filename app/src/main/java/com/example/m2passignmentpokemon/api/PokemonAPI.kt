package com.example.m2passignmentpokemon.api

import com.example.m2passignmentpokemon.model.PokemonModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonAPI {
    @GET("cards")
    suspend fun getPokemonCards(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 20
    ): PokemonModel
}