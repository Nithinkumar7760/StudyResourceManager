package com.pes.studyresourcemanager.wikipedia.data

import com.pes.studyresourcemanager.model.Resource
import java.net.URLEncoder

/**
 * Data class representing article summary details for the detail screen.
 * Contains the extract text and direct link to Wikipedia.
 */
data class WikipediaArticleDetail(
    val title: String,
    val description: String?,
    val extract: String,
    val thumbnailUrl: String?,
    val articleUrl: String
)

/**
 * Repository responsible for fetching Wikipedia data and transforming it
 * into domain models, including the shared Resource data class.
 *
 * Adheres to the Single Responsibility Principle and acts as the Single Source of Truth.
 */
class WikipediaRepository(
    private val apiService: WikipediaApiService = WikipediaApiService.create()
) {

    /**
     * Searches Wikipedia for a query string and maps the raw API response
     * into a list of shared Resource instances.
     *
     * @param query Search keywords entered by the student
     * @return List of shared Resource objects sorted by relevance
     */
    suspend fun searchArticles(query: String): List<Resource> {
        val response = apiService.searchArticles(query = query)
        val pagesMap = response.query?.pages ?: emptyMap()

        // Sort by search result index (relevance ranking returned by Wikipedia)
        return pagesMap.values
            .sortedBy { it.index ?: Int.MAX_VALUE }
            .map { page ->
                // Construct a canonical Wikipedia article web link
                val encodedTitle = URLEncoder.encode(page.title.replace(" ", "_"), "UTF-8")
                val articleUrl = "https://en.wikipedia.org/wiki/$encodedTitle"

                // Map raw Wikipedia DTO to the team's shared Resource data class
                Resource(
                    id = page.pageid.toString(),
                    title = page.title,
                    description = page.description?.ifBlank { "No description available" }
                        ?: "Wikipedia article",
                    thumbnailUrl = page.thumbnail?.source,
                    source = "Wikipedia",
                    url = articleUrl
                )
            }
    }

    /**
     * Fetches detailed summary of an article by its title.
     *
     * @param title Title of the Wikipedia page
     * @return WikipediaArticleDetail containing extract text and web link
     */
    suspend fun getArticleDetail(title: String): WikipediaArticleDetail {
        val summary = apiService.getArticleSummary(title)

        val fallbackUrl = "https://en.wikipedia.org/wiki/${URLEncoder.encode(title.replace(" ", "_"), "UTF-8")}"
        val articleUrl = summary.contentUrls?.desktop?.page ?: fallbackUrl

        return WikipediaArticleDetail(
            title = summary.title,
            description = summary.description,
            extract = summary.extract?.ifBlank { "No extract text available for this topic." }
                ?: "No summary available.",
            thumbnailUrl = summary.thumbnail?.source,
            articleUrl = articleUrl
        )
    }
}
