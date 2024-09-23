package com.example.pokedexandroid.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    fun getPokemonInStateById(id: Int): Pokemon? {
        return _pokemonList.value.find { it.id == id }
    }

    fun getPokemonState(limit: Int) {
        viewModelScope.launch {
            val pokemon = repository.getPokemon(limit)
            println(pokemon)
            _pokemonList.value = pokemon
        }
    }

    suspend fun updatePokemonState(pokemon: Pokemon, viewModel: ViewModel) {
        val currentList = _pokemonList.value.toMutableList()

        // Check if the index is valid
        val index = pokemon.id - 1
        if (index >= 0 && index < currentList.size) {
            // Replace the Pokémon in the list with the updated one
            currentList[index] = pokemon

            // Assign the modified list back to the StateFlow to update the UI
            _pokemonList.value = currentList
        } else {
            println("Index out of bounds: ${pokemon.id}")
        }
    }
}