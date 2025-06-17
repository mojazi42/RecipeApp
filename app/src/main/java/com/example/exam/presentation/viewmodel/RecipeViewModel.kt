package com.example.exam.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam.data.remote.dto.RecipeDetailDto
import com.example.exam.data.remote.dto.RecipeDto
import com.example.exam.domain.repository.RecipeRepository
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

    var currentPage = 1
    var currentQuery = ""
    var hasMoreResults = true


    fun searchRecipes(query: String, isNewSearch: Boolean = false) {
        if (isLoading.value || (!hasMoreResults && !isNewSearch)) return

        viewModelScope.launch {
            try {

                _isLoading.value = true

                if (isNewSearch) {
                    currentPage = 1
                    _recipes.value = emptyList()
                    hasMoreResults = true
                    currentQuery = query
                }

                val result = repository.searchRecipes(query, currentPage)

                if (result.isEmpty()) {
                    hasMoreResults = false
                    return@launch
                }


                _recipes.value += result

                if(result.size < 30){
                    hasMoreResults = false
                }

                currentPage++
                _error.value = null
                currentQuery = query

            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getRecipeDetail(id: Int) {

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val recipe = repository.getRecipeById(id)
                _selectedRecipe.value = recipe

            } catch (e: Exception) {
                _error.value = e.message

            } finally {
                _isLoading.value = false

            }
        }
    }
}