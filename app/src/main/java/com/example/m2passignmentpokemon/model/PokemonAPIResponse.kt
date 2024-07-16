package com.example.m2passignmentpokemon.model


data class PokemonAPIResponse(
    val data: List<PokemonModel>,
    val page: Int,
    val pageSize: Int,
    val totalCount: Int
)