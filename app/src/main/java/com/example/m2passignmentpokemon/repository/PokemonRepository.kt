package com.example.m2passignmentpokemon.repository

import com.example.m2passignmentpokemon.api.PokemonAPI
import com.example.m2passignmentpokemon.dao.PokemonDao
import com.example.m2passignmentpokemon.model.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PokemonRepository {
    suspend fun getPokemonCards(page: Int): Flow<List<Data>>
    suspend fun getPokemonCardById(id: String): Flow<Data?>
}

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokemonAPI,
    private val pokemonDao: PokemonDao
) : PokemonRepository {
    override suspend fun getPokemonCards(page: Int): Flow<List<Data>> = flow {
        try {
            val response = apiService.getPokemonCards(page)
            pokemonDao.insertAll(response.data)
            emit(response.data)
        } catch (e: Exception) {
            emit(pokemonDao.getPokemonCards(page * 20, 20))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getPokemonCardById(id: String): Flow<Data?> = flow {
        emit(pokemonDao.getPokemonCardById(id))
    }.flowOn(Dispatchers.IO)
}
