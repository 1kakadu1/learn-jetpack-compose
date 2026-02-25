package com.example.entertainhub.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entertainhub.data.model.Movie
import com.example.entertainhub.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val popularMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class HomeViewModel(
    private val repository: MovieRepository = MovieRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies() {
        viewModelScope.launch {  // ← Корутина в scope ViewModel
            // 1. Устанавливаем loading = true
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            // 2. Собираем данные из Flow
            repository.getPopularMovies().collect { result ->
                // 3. Обрабатываем результат
                result.onSuccess { movies ->
                    // Успешно - обновляем список
                    _uiState.value = _uiState.value.copy(
                        popularMovies = movies,
                        isLoading = false,
                        error = null
                    )
                }.onFailure { exception ->
                    // Ошибка - сохраняем сообщение
                    _uiState.value = _uiState.value.copy(
                        popularMovies = emptyList(),
                        isLoading = false,
                        error = exception.message ?: "Unknown error"
                    )
                }
            }
        }
    }


    fun retry() {
        loadPopularMovies()
    }
}