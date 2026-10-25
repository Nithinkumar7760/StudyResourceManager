package com.pes.studyresourcemanager.wikipedia.data

import com.google.gson.annotations.SerializedName

/**
 * Top-level response from the Wikipedia Query Search API.
 * API Endpoint: https://en.wikipedia.org/w/api.php?action=query&generator=search...
 */
data class WikipediaSearchResponse(
    @SerializedName("query")
    val query: WikipediaQueryDto?
)

/**
 * Container holding the map of search result pages returned by Wikipedia.
 */
data class WikipediaQueryDto(
    @SerializedName("pages")
    val pages: Map<String, WikipediaSearchPageDto>?
)

/**
 * Individual article item returned in the search results.
 */
data class WikipediaSearchPageDto(
    @SerializedName("pageid")
    val pageid: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("index")
    val index: Int?,

    @SerializedName("thumbnail")
    val thumbnail: WikipediaThumbnailDto?
)

/**
 * Thumbnail image information for a Wikipedia article.
 */
data class WikipediaThumbnailDto(
    @SerializedName("source")
    val source: String,

    @SerializedName("width")
    val width: Int?,

    @SerializedName("height")
    val height: Int?
)

/**
 * Detailed summary response from the Wikipedia REST API.
 * API Endpoint: https://en.wikipedia.org/api/rest_v1/page/summary/{title}
 */
data class WikipediaSummaryResponse(
    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("extract")
    val extract: String?,

    @SerializedName("thumbnail")
    val thumbnail: WikipediaThumbnailDto?,

    @SerializedName("content_urls")
    val contentUrls: WikipediaContentUrlsDto?
)

/**
 * Content URLs container linking to web and mobile views.
 */
data class WikipediaContentUrlsDto(
    @SerializedName("desktop")
    val desktop: WikipediaPageUrlDto?
)

/**
 * Holds direct URL to the full Wikipedia article web page.
 */
data class WikipediaPageUrlDto(
    @SerializedName("page")
    val page: String?
)
