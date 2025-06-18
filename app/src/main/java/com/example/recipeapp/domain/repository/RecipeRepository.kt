package com.example.recipeapp.domain.repository

import com.example.recipeapp.data.remote.dto.RecipeDetailDto
import com.example.recipeapp.data.remote.dto.RecipeDto
import com.example.recipeapp.util.Resource

interface RecipeRepository {
    suspend fun searchRecipes(query: String, page: Int): Resource<List<RecipeDto>>
    suspend fun getRecipeById(id: Int): Resource<RecipeDetailDto>
}