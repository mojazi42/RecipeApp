package com.example.exam.data.remote.api

import com.example.exam.data.remote.dto.RecipeDetailDto
import com.example.exam.data.remote.dto.RecipeResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeFoodApi {

    @GET("api/recipe/search/")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("page") page: Int
    ): RecipeResponseDto


    @GET("api/recipe/get/")
    suspend fun getRecipeById(@Query("id") id: Int) : RecipeDetailDto

}

