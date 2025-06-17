package com.example.exam.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail/{recipeId}") {
        fun createRoute(recipeId: Int) = "detail/$recipeId"
    }
}
