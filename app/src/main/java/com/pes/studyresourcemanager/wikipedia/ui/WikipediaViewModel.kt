package com.pes.studyresourcemanager.wikipedia.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pes.studyresourcemanager.wikipedia.data.WikipediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * ViewModel managing the business logic and UI state for Wikipedia screens.
 *
 * Exposes StateFlow streams consumed by Compose screens, ensuring state survives
 * configuration changes (e.g., screen rotations).
 *
 * @param repository Repository instance used to fetch Wikipedia data
 */
class WikipediaViewModel(
    private val repository: WikipediaRepository = WikipediaRepository()
) : ViewModel() {

    // Internal mutable state for the current search query text
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Internal mutable state for search results
    private val _searchUiState = MutableStateFlow<WikipediaSearchUiState>(WikipediaSearchUiState.Idle)
    val searchUiState: StateFlow<WikipediaSearchUiState> = _searchUiState.asStateFlow()

    // Internal mutable state for article details
    private val _detailUiState = MutableStateFlow<WikipediaDetailUiState>(WikipediaDetailUiState.Loading)
    val detailUiState: StateFlow<WikipediaDetailUiState> = _detailUiState.asStateFlow()

    // Cache the last searched query to support the Retry button on error
    private var lastQuery: String = ""
    // Cache the current article title for detail screen retry
    private var currentDetailTitle: String = ""

    /**
     * Updates the current search query entered by the user.
     */
    fun onQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    /**
     * Executes the search operation against Wikipedia API.
     * Transitions between Loading, Success, Empty, and Error states.
     *
     * @param query The search query string. If null, uses the current searchQuery value.
     */
    fun search(query: String? = null) {
        val targetQuery = (query ?: _searchQuery.value).trim()

        if (targetQuery.isEmpty()) {
            _searchUiState.value = WikipediaSearchUiState.Idle
            return
        }

        lastQuery = targetQuery
        _searchUiState.value = WikipediaSearchUiState.Loading

        viewModelScope.launch {
            try {
                val results = repository.searchArticles(targetQuery)
                if (results.isEmpty()) {
                    _searchUiState.value = WikipediaSearchUiState.Empty
                } else {
                    _searchUiState.value = WikipediaSearchUiState.Success(results)
                }
            } catch (e: IOException) {
                // Network connection failure or timeout
                _searchUiState.value = WikipediaSearchUiState.Error(
                    "Unable to connect to Wikipedia. Please check your internet connection."
                )
            } catch (e: Exception) {
                // Other unexpected failures
                _searchUiState.value = WikipediaSearchUiState.Error(
                    e.localizedMessage ?: "An unexpected error occurred while searching."
                )
            }
        }
    }

    /**
     * Retries the last attempted search query.
     * Called when the student taps the "Retry" button on the error state.
     */
    fun retrySearch() {
        if (lastQuery.isNotEmpty()) {
            search(lastQuery)
        }
    }

    /**
     * Fetches detailed summary extract for a specific article.
     *
     * @param title Wikipedia article title
     */
    fun loadArticleDetail(title: String) {
        currentDetailTitle = title
        _detailUiState.value = WikipediaDetailUiState.Loading

        viewModelScope.launch {
            try {
                val detail = repository.getArticleDetail(title)
                _detailUiState.value = WikipediaDetailUiState.Success(detail)
            } catch (e: IOException) {
                _detailUiState.value = WikipediaDetailUiState.Error(
                    "Failed to load article details. Please check your internet connection."
                )
            } catch (e: Exception) {
                _detailUiState.value = WikipediaDetailUiState.Error(
                    e.localizedMessage ?: "Failed to load article summary."
                )
            }
        }
    }

    /**
     * Retries loading the article details.
     */
    fun retryLoadDetail() {
        if (currentDetailTitle.isNotEmpty()) {
            loadArticleDetail(currentDetailTitle)
        }
    }
}
