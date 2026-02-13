package com.example.entertainhub.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entertainhub.data.mock.SearchMockData
import com.example.entertainhub.data.model.SearchResult
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


data class SearchState(
    val query: String = "",
    val results: List<SearchResult> = emptyList(),
    val isSearching: Boolean = false,
    val error: String? = null
)

@OptIn(FlowPreview::class)
class SearchViewModel : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .collect { query ->
                    if (query.isEmpty()) {
                        _searchState.value = SearchState(query = query)
                    } else {
                        performSearch(query)
                    }
                }
        }
    }

    fun updateQuery(query: String) {
        _searchQuery.value = query
        _searchState.value = _searchState.value.copy(
            query = query,
            isSearching = query.isNotEmpty()
        )
    }

    fun clearQuery() {
        _searchQuery.value = ""
        _searchState.value = SearchState()
    }

    private suspend fun performSearch(query: String) {
        _searchState.value = _searchState.value.copy(isSearching = true)

        try {
            // val results = repository.search(query)

            kotlinx.coroutines.delay(300)  // Имитация задержки
            val allMovies = SearchMockData.searchRezult

            val results = allMovies.filter {
                it.title.contains(query, ignoreCase = true)
            }

            _searchState.value = SearchState(
                query = query,
                results = results,
                isSearching = false
            )
        } catch (e: Exception) {
            _searchState.value = _searchState.value.copy(
                isSearching = false,
                error = e.message
            )
        }
    }
}