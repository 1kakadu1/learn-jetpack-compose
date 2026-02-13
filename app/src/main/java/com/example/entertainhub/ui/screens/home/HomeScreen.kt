package com.example.entertainhub.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.entertainhub.data.mock.MovieMockData
import com.example.entertainhub.ui.components.cards.card_movie.MovieCard
import com.example.entertainhub.ui.components.carousels.media_carousel.MediaCarousel
import com.example.entertainhub.ui.components.scaffolds.MainScaffold
import com.example.entertainhub.ui.components.headers.header_main.HeaderMain
import com.example.entertainhub.ui.components.home_bottom_app_bar.HomeBottomAppBar
import com.example.entertainhub.ui.navigation.LocalNavController
import com.example.entertainhub.ui.navigation.Routes
import com.example.entertainhub.ui.theme.EntertainHubTheme

@Composable
fun HomeScreen() {
    val navController = LocalNavController.current
    val gotoDetail: (String) -> Unit = { id -> navController.navigate(Routes.details(id)) }
    MainScaffold(
        bottomBar = { HomeBottomAppBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            item {
                HeaderMain(
                    onClickSearch = {
                        navController.navigate(Routes.SEARCH)
                    }
                )
            }
            item {
                Box(Modifier.size(26.dp))
            }
            item {
                MovieCard(
                    title = "The Hobbit: An Unexpected Journey The Hobbit: An Unexpected Journey",
                    imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg",
                    ageRating = "6+",
                    language = "ENGLISH",
                    genre = "FANTASY",
                    format = "2D.3D.4DX",
                    onWatchTrailerClick = {},
                    onBookClick = { gotoDetail("featured-movie") }
                )
            }

            item {
                Spacer(modifier = Modifier.height(26.dp))
            }
            item {
                MediaCarousel(
                    title = "Recommended Movies",
                    items = MovieMockData.recommendedMovies,
                    onSeeAllClick = {},
                    onItemClick = { gotoDetail("featured-movie") }
                )
            }
            item {
                Spacer(modifier = Modifier.height(26.dp))
            }
            item {
                MediaCarousel(
                    title = "Recommended Movies",
                    items = MovieMockData.recommendedMovies,
                    onSeeAllClick = {},
                    onItemClick = { gotoDetail("featured-movie") }
                )
            }
            item {
                Spacer(modifier = Modifier.height(26.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    EntertainHubTheme {
        HomeScreen()
    }
}