package com.example.recipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipeapp.presentation.screens.HomeScreen
import com.example.recipeapp.presentation.screens.RecipeDetailScreen
import com.example.recipeapp.presentation.viewmodel.rememberRecipeViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {
    val viewModel = rememberRecipeViewModel()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController, viewModel)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: return@composable
            RecipeDetailScreen(recipeId, viewModel, navController)
        }
    }
}