package com.example.recipeapp.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.data.remote.RetrofitInstance
import com.example.recipeapp.data.repositoryImpl.RecipeRepositoryImpl

@Composable
fun rememberRecipeViewModel(): RecipeViewModel {
    val repository = remember { RecipeRepositoryImpl(RetrofitInstance.api) }
    val factory = remember { RecipeVMFactory(repository) }

    return viewModel(factory = factory)
}