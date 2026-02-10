package com.example.entertainhub.ui.screens.home

//import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.entertainhub.ui.components.cards.card_movie.MovieCard
import com.example.entertainhub.ui.components.carousels.media_carousel.MediaCarousel
import com.example.entertainhub.ui.components.carousels.media_carousel.MediaCarouselItem
import com.example.entertainhub.ui.components.scaffolds.MainScaffold
import com.example.entertainhub.ui.components.headers.header_main.HeaderMain
import com.example.entertainhub.ui.components.home_bottom_app_bar.HomeBottomAppBar
import com.example.entertainhub.ui.theme.EntertainHubTheme

//import androidx.compose.foundation.rememberScrollState

@Composable
fun HomeScreen() {
    val mockItems1 = listOf(
        MediaCarouselItem(
            id = "1",
            title = "SALAAR",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        ),
        MediaCarouselItem(
            id = "2",
            title = "FLASH",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        ),
        MediaCarouselItem(
            id = "3",
            title = "AQUAMAN",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        )
    )

    MainScaffold(
        bottomBar = { HomeBottomAppBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                //.horizontalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
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
            Spacer(modifier = Modifier.height(26.dp))
            MediaCarousel(
                title = "Recommended Movies",
                items = mockItems1,
                onSeeAllClick = {},
                onItemClick = {}
            )
            Spacer(modifier = Modifier.height(26.dp))
            MediaCarousel(
                title = "Recommended Movies",
                items = mockItems1,
                onSeeAllClick = {},
                onItemClick = {}
            )
            Spacer(modifier = Modifier.height(26.dp))
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