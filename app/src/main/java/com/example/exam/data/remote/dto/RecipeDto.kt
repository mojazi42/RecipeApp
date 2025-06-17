package com.example.exam.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RecipeDto(
    val pk: Int,
    val title: String,
    val publisher: String,

    @SerializedName("featured_image")
    val featuredImage: String,

    val description: String?,

    @SerializedName("cooking_instructions")
    val cookingInstructions: String?
)

data class RecipeResponseDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<RecipeDto>
)

data class RecipeDetailDto(
    val pk: Int,
    val title: String,
    val publisher: String,

    @SerializedName("featured_image")
    val featuredImage: String,
    val description: String?,
    val ingredients: List<String>?,

    @SerializedName("source_url")
    val sourceUrl: String,
    @SerializedName("cooking_instructions")
    val cookingInstructions: String?
)
