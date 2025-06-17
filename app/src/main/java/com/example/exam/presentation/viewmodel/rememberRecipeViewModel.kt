package com.example.exam.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exam.data.remote.RetrofitInstance
import com.example.exam.data.repositoryImpl.RecipeRepositoryImpl

@Composable
fun rememberRecipeViewModel(): RecipeViewModel {
    val repository = remember { RecipeRepositoryImpl(RetrofitInstance.api) }
    val factory = remember { RecipeVMFactory(repository) }

    return viewModel(factory = factory)
}