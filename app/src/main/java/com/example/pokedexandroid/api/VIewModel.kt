package com.example.pokedexandroid.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {
    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    init {
        fetchAllPokemon()
    }

    // Fetch Pokémon one at a time using their IDs
    private fun fetchAllPokemon() {
        viewModelScope.launch {
            for (id in 1..251) {
                val pokemon = repository.getPokemonById(id)

                // Add the fetched Pokémon to the list
                _pokemonList.value += pokemon
            }
        }
    }
}