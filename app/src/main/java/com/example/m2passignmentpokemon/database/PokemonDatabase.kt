package com.example.m2passignmentpokemon.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.m2passignmentpokemon.dao.PokemonDao
import com.example.m2passignmentpokemon.model.Data

@Database(entities = [Data::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}