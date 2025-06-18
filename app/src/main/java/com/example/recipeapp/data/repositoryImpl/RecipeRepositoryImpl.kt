package com.example.recipeapp.data.repositoryImpl

import com.example.recipeapp.data.remote.api.RecipeFoodApi
import com.example.recipeapp.data.remote.dto.RecipeDetailDto
import com.example.recipeapp.data.remote.dto.RecipeDto
import com.example.recipeapp.domain.repository.RecipeRepository
import com.example.recipeapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.URLEncoder
import java.net.UnknownHostException

class RecipeRepositoryImpl(private val api: RecipeFoodApi) : RecipeRepository {
    override suspend fun searchRecipes(query: String, page: Int): Resource<List<RecipeDto>> {
        return try {
            val encodedQuery = URLEncoder.encode(query, "UTF-8")
            val response = api.searchRecipes(encodedQuery, page)
            Resource.Success(response.results)
        } catch (e: Exception) {
            Resource.Error(
                message = when (e) {
                    is UnknownHostException -> "No internet connection."
                    is SocketTimeoutException -> "Server timeout, try again."
                    is HttpException -> when (e.code()) {
                        400 -> "Bad request. Please check your input"
                        404 -> "Not found."
                        500 -> "Server error."
                        else -> "HTTP error ${e.code()}: ${e.message()}"
                    }

                    else -> "Unexpected error: ${e.localizedMessage ?: "Unknown error"}"
                },
                throwable = e
            )
        }
    }

    override suspend fun getRecipeById(id: Int): Resource<RecipeDetailDto> {
        return try {
            val response = api.getRecipeById(id)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error", e)
        }
    }
}