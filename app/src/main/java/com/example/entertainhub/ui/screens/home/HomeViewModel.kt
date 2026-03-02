package com.example.entertainhub.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entertainhub.data.model.Movie
import com.example.entertainhub.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DataState<T>(
    val data: List<T> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class HomeUiState(
    val popularMovies: DataState<Movie> = DataState(),
    val topRated: DataState<Movie> = DataState(),
)

class HomeViewModel(
    private val repository: MovieRepository = MovieRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadAllData()
    }

    private fun updatePopularMovies(
        update: (DataState<Movie>) -> DataState<Movie>
    ) {
        _uiState.value = _uiState.value.copy(
            popularMovies = update(_uiState.value.popularMovies)
        )
    }

    private fun updateTopRated(
        update: (DataState<Movie>) -> DataState<Movie>
    ) {
        _uiState.value = _uiState.value.copy(
            topRated = update(_uiState.value.topRated)
        )
    }


    fun loadAllData() {
        loadPopularMovies()
        loadTopRatedMovies()
    }

    fun loadPopularMovies() {
        viewModelScope.launch {
            updatePopularMovies { it.copy(isLoading = true, error = null) }

            repository.getPopularMovies().collect { result ->
                result.onSuccess { movies ->
                    updatePopularMovies {
                        it.copy(
                            data = movies,
                            isLoading = false,
                            error = null
                        )
                    }
                }.onFailure { exception ->
                    updatePopularMovies {
                        it.copy(
                            data = emptyList(),
                            isLoading = false,
                            error = exception.message ?: "Unknown error"
                        )
                    }
                }
            }
        }
    }

    fun loadTopRatedMovies() {
        viewModelScope.launch {
            updateTopRated { it.copy(isLoading = true, error = null) }

            repository.getTopRatedMovies().collect { result ->
                result.onSuccess { movies ->
                    updateTopRated {
                        it.copy(data = movies, isLoading = false, error = null)
                    }
                }.onFailure { exception ->
                    updateTopRated {
                        it.copy(
                            data = emptyList(),
                            isLoading = false,
                            error = exception.message ?: "Unknown error"
                        )
                    }
                }
            }
        }
    }


    fun retry() {
        loadAllData()
    }
}