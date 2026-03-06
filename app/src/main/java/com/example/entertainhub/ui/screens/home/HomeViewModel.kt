package com.example.entertainhub.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entertainhub.data.model.Movie
import com.example.entertainhub.data.model.MoviesResponse
import com.example.entertainhub.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class DataState<T>(
    val data: List<T> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 0,
    val totalPages: Int = 0,
    val hasMore: Boolean = true
)

data class HomeUiState(
    val popularMovies: DataState<Movie> = DataState(),
    val topRated: DataState<Movie> = DataState(),
    val nowPlaying: DataState<Movie> = DataState(),
)

class HomeViewModel(
    private val repository: MovieRepository = MovieRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadAllData()
    }

    private fun updateMovieState(
        update: (DataState<Movie>) -> DataState<Movie>,
        stateSelector: (HomeUiState) -> DataState<Movie>,
        stateCopier: (HomeUiState, DataState<Movie>) -> HomeUiState
    ) {
        _uiState.value = stateCopier(
            _uiState.value,
            update(stateSelector(_uiState.value))
        )
    }

    private fun loadMovies(
        currentState: DataState<Movie>,
        repositoryCall: suspend (page: Int) -> Flow<Result<MoviesResponse>>,
        updateState: (DataState<Movie>) -> Unit,
        refresh: Boolean
    ) {
        if (!refresh && (currentState.isLoadingMore || !currentState.hasMore)) {
            return
        }

        viewModelScope.launch {
            val pageToLoad = if (refresh) 1 else currentState.currentPage + 1

            updateState(
                currentState.copy(
                    isLoading = refresh,
                    isLoadingMore = !refresh,
                    error = null
                )
            )

            try {
                repositoryCall(pageToLoad).first().onSuccess { response ->
                    val newData = if (refresh) {
                        response.results
                    } else {
                        currentState.data + response.results
                    }

                    updateState(
                        DataState(
                            data = newData,
                            isLoading = false,
                            isLoadingMore = false,
                            error = null,
                            currentPage = response.page,
                            totalPages = response.totalPages,
                            hasMore = response.page < response.totalPages
                        )
                    )
                }.onFailure { exception ->
                    updateState(
                        currentState.copy(
                            isLoading = false,
                            isLoadingMore = false,
                            error = exception.message ?: "Unknown error"
                        )
                    )
                }
            } catch (e: Exception) {
                updateState(
                    currentState.copy(
                        isLoading = false,
                        isLoadingMore = false,
                        error = e.message ?: "Unknown error"
                    )
                )
            }
        }
    }


    fun loadAllData() {
        loadPopularMovies(refresh = true)
        loadTopRatedMovies(refresh = true)
        loadNowPlayingMovies(refresh = true)
    }

    fun loadPopularMovies(refresh: Boolean = false) {
        loadMovies(
            currentState = _uiState.value.popularMovies,
            repositoryCall = { page -> repository.getPopularMovies(page) },
            updateState = { newState ->
                _uiState.value = _uiState.value.copy(popularMovies = newState)
            },
            refresh = refresh
        )
    }

    fun loadTopRatedMovies(refresh: Boolean = false) {
        loadMovies(
            currentState = _uiState.value.topRated,
            repositoryCall = { page -> repository.getTopRatedMovies(page) },
            updateState = { newState ->
                _uiState.value = _uiState.value.copy(topRated = newState)
            },
            refresh = refresh
        )
    }

    fun loadNowPlayingMovies(refresh: Boolean = false) {
        loadMovies(
            currentState = _uiState.value.nowPlaying,
            repositoryCall = { page -> repository.getNowPlayingMovies(page) },
            updateState = { newState ->
                _uiState.value = _uiState.value.copy(nowPlaying = newState)
            },
            refresh = refresh
        )
    }


    fun retry() {
        loadAllData()
    }
}