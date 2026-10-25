package com.pes.studyresourcemanager.wikipedia.ui

import com.pes.studyresourcemanager.model.Resource
import com.pes.studyresourcemanager.wikipedia.data.WikipediaArticleDetail

/**
 * Represents the distinct UI states for the Wikipedia Search screen.
 *
 * Implements a strict State Pattern to ensure:
 * 1. Initial idle/prompt state
 * 2. Loading state while network request is in flight
 * 3. Success state showing the mapped Resource list
 * 4. Empty state when no matching articles exist
 * 5. Error state with error details and retry capability
 */
sealed interface WikipediaSearchUiState {
    data object Idle : WikipediaSearchUiState
    data object Loading : WikipediaSearchUiState
    data class Success(val resources: List<Resource>) : WikipediaSearchUiState
    data object Empty : WikipediaSearchUiState
    data class Error(val message: String) : WikipediaSearchUiState
}

/**
 * Represents the UI states for the Wikipedia Article Detail screen.
 */
sealed interface WikipediaDetailUiState {
    data object Loading : WikipediaDetailUiState
    data class Success(val detail: WikipediaArticleDetail) : WikipediaDetailUiState
    data class Error(val message: String) : WikipediaDetailUiState
}
