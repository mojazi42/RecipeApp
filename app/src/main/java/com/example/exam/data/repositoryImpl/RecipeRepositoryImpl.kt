package com.example.exam.data.repositoryImpl

import com.example.exam.data.remote.api.RecipeFoodApi
import com.example.exam.data.remote.dto.RecipeDetailDto
import com.example.exam.data.remote.dto.RecipeDto
import com.example.exam.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl(private val api : RecipeFoodApi) : RecipeRepository {
    override suspend fun searchRecipes(query: String, page: Int): List<RecipeDto>{
        return withContext(Dispatchers.IO){
            val response = api.searchRecipes(query, page)
            response.results
        }
    }
    override suspend fun getRecipeById(id: Int): RecipeDetailDto {
        return withContext(Dispatchers.IO){
            api.getRecipeById(id)

        }
    }

}