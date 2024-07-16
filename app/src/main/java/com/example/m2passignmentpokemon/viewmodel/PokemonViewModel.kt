package com.example.m2passignmentpokemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m2passignmentpokemon.model.Data
import com.example.m2passignmentpokemon.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemonListState = MutableStateFlow(PokemonListState())
    val pokemonListState: StateFlow<PokemonListState> = _pokemonListState.asStateFlow()

    private var currentPage = 0

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            repository.getPokemonCards(currentPage)
                .onStart { _pokemonListState.value = _pokemonListState.value.copy(isLoading = true) }
                .catch { e ->
                    _pokemonListState.value = _pokemonListState.value.copy(isLoading = false)
                    val localData = repository.getPokemonCards(currentPage).firstOrNull()
                    _pokemonListState
                }
                .collect { cards ->
                    _pokemonListState.value = _pokemonListState.value.copy(
                        isLoading = false,
                        pokemonCards = cards
                    )
                }
        }
    }

    fun loadNextPage() {
        viewModelScope.launch {
            currentPage++
            repository.getPokemonCards(currentPage)
                .catch {  }
                .collect { newCards ->
                    _pokemonListState.value = _pokemonListState.value.copy(
                        pokemonCards = _pokemonListState.value.pokemonCards + newCards
                    )
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _pokemonListState.value = _pokemonListState.value.copy(searchQuery = query)
        filterPokemonCards()
    }

    fun onSortOrderChanged(order: SortOrder) {
        _pokemonListState.value = _pokemonListState.value.copy(sortOrder = order)
        filterPokemonCards()
    }

    private fun filterPokemonCards() {
        viewModelScope.launch {
            repository.getPokemonCards(currentPage)
                .collect { cards ->
                    val filteredList = cards
                        .filter { it.name.contains(_pokemonListState.value.searchQuery, ignoreCase = true) }
                        .sortedWith(
                            when (_pokemonListState.value.sortOrder) {
                                SortOrder.BY_LEVEL -> compareBy { it.level?.toIntOrNull() ?: Int.MAX_VALUE }
                                SortOrder.BY_HP -> compareBy { it.hp.toIntOrNull() ?: 0 }
                                else -> compareBy { it.name }
                            }
                        )
                    _pokemonListState.value = _pokemonListState.value.copy(pokemonCards = filteredList)
                }
        }
    }

    fun getPokemonCardById(id: String): StateFlow<Data?> {
        val pokemonCard = MutableStateFlow<Data?>(null)
        viewModelScope.launch {
            repository.getPokemonCardById(id)
                .collect {card ->
                    pokemonCard.value = card
                }
        }
        return pokemonCard.asStateFlow()
    }
}

enum class SortOrder {
    NONE, BY_LEVEL, BY_HP
}

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemonCards: List<Data> = emptyList(),
    val searchQuery: String = "",
    val sortOrder: SortOrder = SortOrder.NONE
)