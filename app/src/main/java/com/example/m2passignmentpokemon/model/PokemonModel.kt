package com.example.m2passignmentpokemon.model

data class PokemonModel(
    val count: Int,
    val `data`: List<Data>,
    val page: Int,
    val pageSize: Int,
    val totalCount: Int
)