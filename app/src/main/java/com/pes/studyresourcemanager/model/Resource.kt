package com.pes.studyresourcemanager.model

/**
 * Shared data model representing any educational resource in the app.
 *
 * Designed to unify resources from different sources (Wikipedia articles,
 * Open Library books, and user-saved bookmarks).
 *
 * @param id Unique identifier for the resource (e.g., Wikipedia page ID or Open Library key)
 * @param title Title of the study resource
 * @param description Short description or extract summarizing the resource
 * @param thumbnailUrl Optional URL to a thumbnail/cover image
 * @param source Origin of the resource ("Wikipedia", "Open Library", etc.)
 * @param url Web URL to view the full resource online
 */
data class Resource(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String?,
    val source: String,
    val url: String
)
