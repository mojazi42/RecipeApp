package com.example.recipeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.remote.dto.RecipeDetailDto
import com.example.recipeapp.data.remote.dto.RecipeDto
import com.example.recipeapp.domain.repository.RecipeRepository
import com.example.recipeapp.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _recipes = MutableStateFlow<List<RecipeDto>>(emptyList())
    val recipes: StateFlow<List<RecipeDto>> = _recipes

    private val _selectedRecipe = MutableStateFlow<RecipeDetailDto?>(null)
    val selectedRecipe: StateFlow<RecipeDetailDto?> = _selectedRecipe

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    var currentPage = 1
    var currentQuery = ""
    var hasMoreResults = true


    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun searchRecipes(query: String, isNewSearch: Boolean = false) {
        if (_isLoading.value || (!hasMoreResults && !isNewSearch)) return

        viewModelScope.launch {
            _isLoading.value = true

            if (isNewSearch) {
                currentPage = 1
                _recipes.value = emptyList()
                hasMoreResults = true
            }


            when (val result = repository.searchRecipes(query, currentPage)) {
                is Resource.Success -> {

                    val newResults = result.data
                    if (newResults.isEmpty()) {
                        hasMoreResults = false
                    } else {
                        _recipes.value = _recipes.value + newResults
                        currentPage++
                    }
                    if (newResults.size < 20) {
                        hasMoreResults = false
                    }
                    _error.value = null
                }

                is Resource.Error -> {
                    _error.value = result.message
                }

                else -> {}
            }


            _isLoading.value = false
        }

    }


    fun getRecipeDetail(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            when (val result = repository.getRecipeById(id)) {
                is Resource.Success -> _selectedRecipe.value = result.data
                is Resource.Error -> _error.value = result.message
                else -> {}
            }

            _isLoading.value = false
        }
    }
}