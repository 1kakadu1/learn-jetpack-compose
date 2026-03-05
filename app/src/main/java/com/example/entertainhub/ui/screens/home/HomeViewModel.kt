package com.example.entertainhub.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entertainhub.data.model.Movie
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
    val error: String? = null
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
        repositoryCall: () -> Flow<Result<List<Movie>>>,
        updateState: ((DataState<Movie>) -> DataState<Movie>) -> Unit
    ) {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true, error = null) }

            try {
                repositoryCall().first().onSuccess { movies ->
                    updateState {
                        it.copy(data = movies, isLoading = false, error = null)
                    }
                }.onFailure { exception ->
                    updateState {
                        it.copy(
                            data = emptyList(),
                            isLoading = false,
                            error = exception.message ?: "Unknown error"
                        )
                    }
                }
            } catch (e: Exception) {
                updateState {
                    it.copy(error = e.message, isLoading = false)
                }
            }
        }
    }


    fun loadAllData() {
        loadPopularMovies()
        loadTopRatedMovies()
        loadNowPlayingMovies()
    }

    fun loadPopularMovies() {
        loadMovies(
            repositoryCall = { repository.getPopularMovies() },
            updateState = { update ->
                _uiState.value = _uiState.value.copy(
                    popularMovies = update(_uiState.value.popularMovies)
                )
            }
        )
    }

    fun loadTopRatedMovies() {
        loadMovies(
            repositoryCall = { repository.getTopRatedMovies() },
            updateState = { update ->
                _uiState.value = _uiState.value.copy(
                    topRated = update(_uiState.value.topRated)
                )
            }
        )
    }

    fun loadNowPlayingMovies() {
        loadMovies(
            repositoryCall = { repository.getNowPlayingMovies() },
            updateState = { update ->
                _uiState.value = _uiState.value.copy(
                    nowPlaying = update(_uiState.value.nowPlaying)
                )
            }
        )
    }


    fun retry() {
        loadAllData()
    }
}