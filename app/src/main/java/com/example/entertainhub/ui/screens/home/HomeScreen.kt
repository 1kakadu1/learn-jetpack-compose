package com.example.entertainhub.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.entertainhub.ui.components.cards.card_movie.MovieCard
import com.example.entertainhub.ui.components.scaffolds.MainScaffold
import com.example.entertainhub.ui.components.headers.header_main.HeaderMain
import com.example.entertainhub.ui.components.home_bottom_app_bar.HomeBottomAppBar
import com.example.entertainhub.ui.theme.EntertainHubTheme

@Composable
fun HomeScreen(name: String, modifier: Modifier = Modifier) {
    MainScaffold(
        bottomBar = { HomeBottomAppBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
        ) {
            HeaderMain()
            Box(Modifier.size(26.dp))
            MovieCard(
                title = "The Hobbit: An Unexpected Journey The Hobbit: An Unexpected Journey",
                imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg",
                ageRating = "6+",
                language = "ENGLISH",
                genre = "FANTASY",
                format = "2D.3D.4DX",
                onWatchTrailerClick = {},
                onBookClick = {}
            )
            Box(Modifier.size(26.dp))
            Text(
                text = "Hello $name! ${MaterialTheme.colorScheme.background}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = modifier,

                )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    EntertainHubTheme {
        HomeScreen("Android")
    }
}