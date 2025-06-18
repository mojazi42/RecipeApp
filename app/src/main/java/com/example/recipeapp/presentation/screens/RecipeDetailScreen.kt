package com.example.recipeapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recipeapp.presentation.viewmodel.RecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(recipeId: Int, viewModel: RecipeViewModel, navController: NavController) {


    val recipe by viewModel.selectedRecipe.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    var isFavorite by rememberSaveable { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(recipeId) {
        viewModel.getRecipeDetail(recipeId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Recipe Detail", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    })
                    {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        isFavorite = !isFavorite
                    })
                    {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = if (isFavorite) "Remove from Favorites" else "Add to Favorites",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    )
    { innerPadding ->

        when {
            isLoading -> Text("Loading...", modifier = Modifier.padding(16.dp))

            error != null -> {
                Text(
                    "Error: $error",
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }

            recipe != null -> {
                val selected = recipe!!
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    item {
                        Text(
                            text = selected.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        AsyncImage(
                            model = selected.featuredImage,
                            contentDescription = selected.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(vertical = 12.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )

                        Spacer(Modifier.height(4.dp))

                        Text(
                            text = "Publisher: ${selected.publisher}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Spacer(Modifier.height(16.dp))

                        Text("Ingredients", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                    }
                    items(selected.ingredients ?: emptyList()) { ingredient ->
                        Text("• $ingredient", fontSize = 16.sp, lineHeight = 22.sp)
                    }
                    item {
                        Spacer(Modifier.height(16.dp))
                        Text("Instructions", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            selected.cookingInstructions ?: "No instructions available.",
                            fontSize = 16.sp,
                            lineHeight = 22.sp
                        )

                        Spacer(Modifier.height(24.dp))

                        Text("More Information", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "View Full Recipe",
                            color = Color(0xFF1E88E5),
                            fontSize = 16.sp,
                            modifier = Modifier
                                .clickable { uriHandler.openUri(selected.sourceUrl) }
                                .padding(vertical = 4.dp)
                        )
                        Spacer(Modifier.height(32.dp))
                    }
                }
            }
        }
    }
}



