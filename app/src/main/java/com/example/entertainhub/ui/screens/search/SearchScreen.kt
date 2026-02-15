package com.example.entertainhub.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as itemsGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.entertainhub.data.mock.MovieMockData
import com.example.entertainhub.data.model.SearchResult
import com.example.entertainhub.ui.components.cards.card_small.CardSmall
import com.example.entertainhub.ui.components.carousels.media_carousel.MediaCarousel
import com.example.entertainhub.ui.components.scaffolds.MainScaffold
import com.example.entertainhub.ui.components.top_bars.search_topbar.SearchTopBar
import com.example.entertainhub.ui.theme.EntertainHubTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment

@Composable
fun SearchScreen(onBack: () -> Unit) {
    val viewModel = viewModel<SearchViewModel>()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchState by viewModel.searchState.collectAsState()
    SearchView(
        onBack = onBack,
        search = searchQuery,
        onQueryChange = viewModel::updateQuery,
        searchState = searchState
    )
}

@Composable
fun SearchView(
    onBack: () -> Unit,
    search: String,
    onQueryChange: (String) -> Unit,
    searchState: SearchState,
    isList: Boolean = false
) {
    MainScaffold(
        topBar = {
            SearchTopBar(
                onBack = onBack,
                search = search,
                onChangeSearch = onQueryChange
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(Modifier.height(20.dp))
            MediaCarousel(
                title = "They often search for",
                items = MovieMockData.recommendedMovies,
                onSeeAllClick = {},
                onItemClick = {},
                showSeeAll = false
            )
            Spacer(Modifier.height(20.dp))
            SearchResults(
                query = search,
                state = searchState,
                onItemClick = {},
                isList= isList
            )
        }
    }
}

@Composable
private fun SearchResults(
    query: String,
    state: SearchState,
    onItemClick: (String) -> Unit,
    isList: Boolean = false
) {
    when {
        query.isEmpty() -> EmptySearchState()
        state.isSearching -> LoadingState()
        state.results.isEmpty() -> NoResultsState()
        else -> {
            if(isList){
                SearchResultsList(
                    results = state.results,
                    onItemClick = onItemClick
                )
            }else{
                SearchResultsGrid(
                    results = state.results,
                    onItemClick = onItemClick
                )
            }
        }
    }
}

@Composable
private fun EmptySearchState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Search for movies and games",
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun NoResultsState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "No results found",
                color = Color.White,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Try a different search",
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun SearchResultsList(
    results: List<SearchResult>,
    onItemClick: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                "Found ${results.size} results",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        items(results) { result ->
            CardSmall(
                title = result.title,
                imageUrl = result.image,
                onClick = { onItemClick(result.id) }
            )
        }
    }
}

@Composable
private fun SearchResultsGrid(
    results: List<SearchResult>,
    onItemClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 162.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                "Found ${results.size} results",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
        itemsGrid(
            items = results,
            key = { it.id }
        ) { item ->
            CardSmall(
                title = item.title,
                imageUrl = item.title,
                onClick = { onItemClick(item.id) }
            )
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    EntertainHubTheme {
        SearchScreen(
            onBack = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenResultsPreview() {
    EntertainHubTheme {
        SearchView(
            search = "Batman",
            searchState = SearchState(
                query = "Batman",
                results = listOf(
                    SearchResult("1", "THE BATMAN", ""),
                    SearchResult("2", "BATMAN BEGINS", ""),
                    SearchResult("3", "THE BATMAN", ""),
                    SearchResult("4", "BATMAN BEGINS", ""),
                    SearchResult("5", "THE BATMAN", ""),
                    SearchResult("6", "BATMAN BEGINS", "")
                )
            ),
            onQueryChange = {},
            onBack = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenResultsListPreview() {
    EntertainHubTheme {
        SearchView(
            search = "Batman",
            searchState = SearchState(
                query = "Batman",
                results = listOf(
                    SearchResult("1", "THE BATMAN", ""),
                    SearchResult("2", "BATMAN BEGINS", ""),
                    SearchResult("3", "THE BATMAN", ""),
                    SearchResult("4", "BATMAN BEGINS", ""),
                    SearchResult("5", "THE BATMAN", ""),
                    SearchResult("6", "BATMAN BEGINS", "")
                )
            ),
            onQueryChange = {},
            onBack = {},
            isList = true
        )
    }
}