package com.pes.studyresourcemanager.wikipedia.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit interface defining Wikipedia API endpoints.
 *
 * Uses Kotlin coroutines (suspend functions) so network calls run
 * asynchronously off the main UI thread.
 */
interface WikipediaApiService {

    /**
     * Search articles matching a search query.
     * Uses Wikipedia Action API with generator=search and extracts thumbnails + descriptions.
     */
    @GET("w/api.php")
    suspend fun searchArticles(
        @Query("action") action: String = "query",
        @Query("generator") generator: String = "search",
        @Query("gsrsearch") query: String,
        @Query("gsrlimit") limit: Int = 20,
        @Query("prop") prop: String = "pageimages|description",
        @Query("piprop") piprop: String = "thumbnail",
        @Query("pithumbsize") thumbSize: Int = 200,
        @Query("format") format: String = "json"
    ): WikipediaSearchResponse

    /**
     * Fetch article summary extract and thumbnail.
     * Uses Wikipedia REST API v1.
     */
    @GET("api/rest_v1/page/summary/{title}")
    suspend fun getArticleSummary(
        @Path("title") title: String
    ): WikipediaSummaryResponse

    companion object {
        private const val BASE_URL = "https://en.wikipedia.org/"

        /**
         * Factory function to create an instance of WikipediaApiService using Retrofit.
         */
        fun create(): WikipediaApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WikipediaApiService::class.java)
        }
    }
}
