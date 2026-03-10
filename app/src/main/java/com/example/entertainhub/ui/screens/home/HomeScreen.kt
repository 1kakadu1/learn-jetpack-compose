package com.example.entertainhub.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.entertainhub.data.model.Movie
import com.example.entertainhub.ui.components.cards.card_movie.MovieCard
import com.example.entertainhub.ui.components.cards.card_movie.MovieCardPlaceholder
import com.example.entertainhub.ui.components.carousels.media_carousel.MediaCarousel
import com.example.entertainhub.ui.components.carousels.media_carousel.MediaCarouselItem
import com.example.entertainhub.ui.components.scaffolds.MainScaffold
import com.example.entertainhub.ui.components.headers.header_main.HeaderMain
import com.example.entertainhub.ui.components.home_bottom_app_bar.HomeBottomAppBar
import com.example.entertainhub.ui.navigation.Routes
import com.example.entertainhub.ui.theme.DarkMain
import com.example.entertainhub.ui.theme.EntertainHubTheme
import com.example.entertainhub.utils.SetSystemBarsColor
import kotlin.Boolean
import kotlin.String

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel = viewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val onNavigateMovieDetail: (String) -> Unit =
        { id -> navController.navigate(Routes.details(id)) }
    val onNavigateSearch: () -> Unit = { navController.navigate(Routes.SEARCH) }
    val onLoadMore: (type: String)-> Unit = { type ->
        if(type === "top"){
            viewModel.loadTopRatedMovies()
        }else{
            viewModel.loadPopularMovies()
        }
    }
    HomeView(
        onNavigateMovieDetail = onNavigateMovieDetail,
        onNavigateSearch = onNavigateSearch,
        uiState = uiState,
        onRefresh = { viewModel.retry() },
        onLoadMore = onLoadMore
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    onNavigateMovieDetail: (String) -> Unit,
    onNavigateSearch: () -> Unit,
    uiState: HomeUiState,
    onRefresh: () -> Unit,
    onLoadMore: (type: String)-> Unit
) {
    val isLoadingAll =
        uiState.popularMovies.isLoading || uiState.topRated.isLoading || uiState.nowPlaying.isLoading
    val nowPlaying: Movie? = if(uiState.nowPlaying.data.isEmpty()) null else uiState.nowPlaying.data[0]
    SetSystemBarsColor(
        statusBarColor = DarkMain,
        darkIcons = false,
        restoreToTheme = false
    )
    MainScaffold(
        bottomBar = { HomeBottomAppBar() }
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = isLoadingAll,
            onRefresh = { onRefresh() },
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                item {
                    HeaderMain(
                        onClickSearch = onNavigateSearch
                    )
                }
                item {
                    Box(Modifier.size(26.dp))
                }
                item {
                    if (uiState.nowPlaying.isLoading) {
                        MovieCardPlaceholder()
                    } else if(nowPlaying != null) {
                        MovieCard(
                            title = nowPlaying.title,
                            imageUrl = nowPlaying.posterUrl,
                            ageRating = nowPlaying.rating.toString(),
                            language = nowPlaying.originalLanguage,
                            genre = nowPlaying.genres.toString(),
                            format = "2D.3D.4DX",
                            onWatchTrailerClick = {},
                            onBookClick = { onNavigateMovieDetail(nowPlaying.id.toString()) },
                        )
                    }

                }

                item {
                    Spacer(modifier = Modifier.height(26.dp))
                }
                item {
                    MediaCarousel(
                        title = "Popular movies",
                        items = uiState.popularMovies.data.map {
                            MediaCarouselItem(
                                id = it.id.toString(),
                                title = it.title,
                                imageUrl = it.posterUrl
                            )
                        },
                        onSeeAllClick = {},
                        onItemClick = onNavigateMovieDetail,
                        isLoading = uiState.popularMovies.isLoading,
                        isLoadingMore = uiState.popularMovies.isLoadingMore,
                        hasMore = uiState.popularMovies.hasMore,
                        onLoadMore= { onLoadMore("popular") }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(26.dp))
                }
                item {
                    MediaCarousel(
                        title = "Top movies",
                        items = uiState.topRated.data.map {
                            MediaCarouselItem(
                                id = it.id.toString(),
                                title = it.title,
                                imageUrl = it.posterUrl
                            )
                        },
                        onSeeAllClick = {},
                        onItemClick = onNavigateMovieDetail,
                        isLoading = uiState.topRated.isLoading,
                        isLoadingMore = uiState.topRated.isLoadingMore,
                        hasMore = uiState.topRated.hasMore,
                        onLoadMore= { onLoadMore("top") }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    EntertainHubTheme {
        HomeView(
            onNavigateMovieDetail = {},
            onNavigateSearch = {},
            onRefresh = {},
            uiState = HomeUiState(
                popularMovies = DataState(isLoading = false),
                topRated = DataState(isLoading = false)
            ),
            onLoadMore = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreviewLoading() {
    EntertainHubTheme {
        HomeView(
            onNavigateMovieDetail = {},
            onNavigateSearch = {},
            onRefresh = {},
            uiState = HomeUiState(
                popularMovies = DataState(isLoading = true),
                topRated = DataState(isLoading = true),
                nowPlaying = DataState(isLoading = true)
            ),
            onLoadMore = {}
        )
    }
}