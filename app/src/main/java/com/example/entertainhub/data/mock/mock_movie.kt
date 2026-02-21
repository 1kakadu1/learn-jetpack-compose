package com.example.entertainhub.data.mock

import com.example.entertainhub.data.model.MovieDetailData
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

    val movieDetail = MovieDetailData(
        id = "1",
        slug = "hobbit",
        name = recommendedMovies[0].title,
        category = "fantasy",
        quality = "2D.3D.4DX",
        image = recommendedMovies[0].imageUrl,
        images = listOf(
            "https://preview.redd.it/whos-your-favorite-hobbit-v0-374w0yidtd0b1.png?width=640&crop=smart&auto=webp&s=cd9b074477530f9db94613eb1e410d1490cb4bf6",
            "https://i.guim.co.uk/img/media/863d2a87325a2cbc523389336def51a166e0ee6b/0_186_4700_2820/master/4700.jpg?width=1200&height=1200&quality=85&auto=format&fit=crop&s=d3d985dbd897e3f34277bfc1aace15a4",
            "https://s09.stc.yc.kpcdn.net/share/i/12/5786870/de-1200.jpg",
            "https://allbestmovies.ru/uploads/posts/2012-12/1354963178_hobbit.jpg",
            "https://lumiere.su/wp-content/uploads/2012/12/the_hobbit_an_unexpected_journey.jpg",
            "https://i.ytimg.com/vi/qXN_euK2uF4/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLDN8IO7p1TFZTXqWcSnsx2KuDQxVA",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQboORqpdd6FxB4JTf7nWZ0DYHvZLxGG2Oa_w&s"
        ),
        description = "Evil Dead is a 2013American supernatural horror film directed by Fade Alvarez who co-wrote the screenplay with Rode Sayagues. Dubbed a re-imagining of",
        duration = "1hr:38min",
        date = "21 April 2023",
        language = "English"
    )
}