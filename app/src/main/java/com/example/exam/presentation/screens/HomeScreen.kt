package com.example.exam.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.exam.R
import com.example.exam.navigation.Screen
import com.example.exam.presentation.viewmodel.RecipeViewModel
import kotlinx.coroutines.flow.distinctUntilChanged


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: RecipeViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painterResource(id = R.drawable.filter),
                            contentDescription = "Filter Icon",
                            modifier = Modifier.size(26.dp)
                        )
                    }
                },
                title = { Text("Meals Recipes") },
            )
        }
    ) { innerPadding ->

        val recipes by viewModel.recipes.collectAsState()
        val isLoading by viewModel.isLoading.collectAsState()
        val error by viewModel.error.collectAsState()

        var query by rememberSaveable { mutableStateOf("") }

        LaunchedEffect(Unit) {
            viewModel.searchRecipes(query, isNewSearch = true)
        }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            SimpleSearchBar(
                query = query,
                onQueryChange = {
                    query = it
                    viewModel.searchRecipes(query, isNewSearch = true)
                })

            if (error != null) {
                Text("Error: $error", color = Color.Red, modifier = Modifier.padding(16.dp))
            }

            if (isLoading) {
                Text("Loading...", modifier = Modifier.padding(16.dp))
            }
            if (!isLoading && recipes.isEmpty()) {
                Text("No recipes found for \"$query\"", modifier = Modifier.padding(16.dp))
            }


            val gridState = rememberLazyGridState()
            LaunchedEffect(gridState) {
                snapshotFlow {
                    val lastVisibleItem =
                        gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                    val totalItemsCount = gridState.layoutInfo.totalItemsCount
                    Pair(lastVisibleItem, totalItemsCount)
                }.distinctUntilChanged()
                    .collect { (lastVisible, total) ->
                        if (lastVisible >= total - 4 && !isLoading && viewModel.hasMoreResults) {
                            viewModel.searchRecipes(query, isNewSearch = false)
                        }
                    }
            }


            LazyVerticalGrid(
                state = gridState,
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recipes) { meal ->
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .height(220.dp)
                            .clickable {
                                navController.navigate(Screen.Detail.createRoute(meal.pk))
                            },
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp)
                        ) {
                            AsyncImage(
                                model = meal.featuredImage,
                                contentDescription = "Recipe image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(115.dp)
                                    .clip(RoundedCornerShape(12.dp))
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = meal.title,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                fontSize = 16.sp,
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.Black
                            )

                            Text(
                                text = meal.publisher,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}
