package com.example.m2passignmentpokemon.module

import android.content.Context
import androidx.room.Room
import com.example.m2passignmentpokemon.api.PokemonAPI
import com.example.m2passignmentpokemon.dao.PokemonDao
import com.example.m2passignmentpokemon.database.PokemonDatabase
import com.example.m2passignmentpokemon.repository.PokemonRepository
import com.example.m2passignmentpokemon.repository.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.pokemontcg.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PokemonAPI {
        return retrofit.create(PokemonAPI::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonDatabase(@ApplicationContext appContext: Context): PokemonDatabase {
        return Room.databaseBuilder(
            appContext,
            PokemonDatabase::class.java,
            "pokemon_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(database: PokemonDatabase): PokemonDao {
        return database.pokemonDao()
    }

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonAPI, pokemonDao: PokemonDao): PokemonRepository {
        return PokemonRepositoryImpl(api, pokemonDao)
    }
}