package com.example.entertainhub.data.mock

import com.example.entertainhub.ui.components.carousels.media_carousel.MediaCarouselItem

object MovieMockData {

    val recommendedMovies = listOf(
        MediaCarouselItem(
            id = "1",
            title = "THE HOBBIT: AN UNEXPECTED JOURNEY",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        ),
        MediaCarouselItem(
            id = "2",
            title = "FLASH (2023)",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        ),
        MediaCarouselItem(
            id = "3",
            title = "AQUAMAN",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        ),
        MediaCarouselItem(
            id = "4",
            title = "THE BATMAN",
            imageUrl = ""
        )
    )

    val trendingMovies = listOf(
        MediaCarouselItem(
            id = "5",
            title = "OPPENHEIMER",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        ),
        MediaCarouselItem(
            id = "6",
            title = "BARBIE",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        ),
        MediaCarouselItem(
            id = "7",
            title = "DUNE: PART TWO",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        )
    )

    val newReleases = listOf(
        MediaCarouselItem(
            id = "8",
            title = "DEADPOOL & WOLVERINE",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        ),
        MediaCarouselItem(
            id = "9",
            title = "INSIDE OUT 2",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        )
    )


    val featuredMovie = MediaCarouselItem(
        id = "featured-1",
        title = "THE HOBBIT: AN UNEXPECTED JOURNEY",
        imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
    )
}