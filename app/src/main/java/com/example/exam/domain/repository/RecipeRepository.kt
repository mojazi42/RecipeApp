package com.example.exam.domain.repository

import com.example.exam.data.remote.dto.RecipeDetailDto
import com.example.exam.data.remote.dto.RecipeDto

interface RecipeRepository {
    suspend fun searchRecipes(query: String, page: Int): List<RecipeDto>
    suspend fun getRecipeById(id: Int): RecipeDetailDto
}