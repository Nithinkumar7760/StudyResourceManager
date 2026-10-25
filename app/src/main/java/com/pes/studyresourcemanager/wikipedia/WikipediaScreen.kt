package com.pes.studyresourcemanager.wikipedia

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pes.studyresourcemanager.wikipedia.ui.WikipediaSearchScreen
import com.pes.studyresourcemanager.wikipedia.ui.WikipediaViewModel

/**
 * Entry screen for the Wikipedia module.
 *
 * Provides default ViewModel resolution and delegates to WikipediaSearchScreen.
 *
 * @param onResourceClick Callback triggered when a student selects an article
 * @param viewModel ViewModel holding search state
 */
@Composable
fun WikipediaScreen(
    onResourceClick: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: WikipediaViewModel = viewModel()
) {
    WikipediaSearchScreen(
        viewModel = viewModel,
        onResourceClick = onResourceClick,
        modifier = modifier
    )
}
