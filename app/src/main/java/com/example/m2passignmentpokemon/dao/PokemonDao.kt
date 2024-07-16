package com.example.m2passignmentpokemon.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.m2passignmentpokemon.model.Data

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon_table LIMIT :limit OFFSET :offset")
    fun getPokemonCards(offset: Int, limit: Int): List<Data>

    @Query("SELECT * FROM pokemon_table WHERE id = :id")
    fun getPokemonCardById(id: String): Data?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pokemonCards: List<Data>)
}