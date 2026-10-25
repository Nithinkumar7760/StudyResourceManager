# Study Resource Manager - Project Status & Tracking

This document tracks progress across the three independent modules of the Study Resource Manager project.

---

## 1. Wikipedia Piece (Owner: Student 1)

### Status: Completed (Implemented and verified on `wikipedia` branch)

- [x] Shared data model `Resource` created under `model/`
- [x] App shell with 3-tab Bottom Navigation (`Wikipedia`, `Books`, `Saved`) using Navigation Compose
- [x] Retrofit, Gson, Coil, and Navigation dependencies integrated in `libs.versions.toml`
- [x] `INTERNET` permission added in `AndroidManifest.xml`
- [x] Wikipedia API service configuration (Query Search API & REST Summary API)
- [x] DTO response models for Wikipedia search & page summary
- [x] Repository layer with mapper from Wikipedia items to shared `Resource` model
- [x] Wikipedia search ViewModel with StateFlow handling Loading, Success, Error, and Empty states
- [x] Wikipedia search UI with search input bar and results list (thumbnail, title, description)
- [x] Wikipedia detail screen showing title, thumbnail, full summary extract, and "Open in Browser" button
- [x] Error handling with retry action and empty state placeholders

---

## 2. Open Library Piece (Owner: Student 2)

### Status: To Do

- [ ] Define Open Library REST API interface (e.g. `https://openlibrary.org/search.json?q={query}`)
- [ ] Create data models for Open Library books and cover image URLs
- [ ] Create repository mapping Open Library books into the shared `Resource` data class
- [ ] Create `OpenLibraryViewModel` with UI state management (Loading / Success / Error / Empty)
- [ ] Implement Book Search screen with query input and scrollable book list with covers
- [ ] Implement Book Details view with subjects, author, publish year, and description
- [ ] Replace placeholder in `com.pes.studyresourcemanager.openlibrary.OpenLibraryScreen`

---

## 3. Bookmarks & Notes Piece (Owner: Student 3)

### Status: To Do

- [ ] Add Room dependencies (`androidx.room:room-runtime`, `androidx.room:room-ktx`, and KSP plugin)
- [ ] Create Room Entity for saved `Resource` items
- [ ] Create Room Entity for personal user study notes (title, note text, timestamp, linked resource ID)
- [ ] Create Room DAOs for bookmarking and notes CRUD operations
- [ ] Create Room Database class with migrations/fallback
- [ ] Create `BookmarksViewModel` to expose Flow of saved resources and notes
- [ ] Implement bookmark toggle button on resource detail screens
- [ ] Implement Saved resources list screen with filter/tabs for bookmarks and personal notes
- [ ] Implement Add/Edit note dialog or bottom sheet
- [ ] Replace placeholder in `com.pes.studyresourcemanager.bookmarks.BookmarksScreen`

---

## Integration Notes for Teammates

### 1. The Shared `Resource` Model
All three modules share a unified model located at:
`com.pes.studyresourcemanager.model.Resource`

```kotlin
data class Resource(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String?,
    val source: String,
    val url: String
)
```
- **Wikipedia** sets `source = "Wikipedia"`
- **Open Library** sets `source = "Open Library"`
- **Bookmarks & Notes** saves instances of `Resource` so any item can be bookmarked regardless of where it came from.

### 2. Screen Plug-in Points
- Open Library teammate: work entirely inside package `com.pes.studyresourcemanager.openlibrary`. Your entry point is `OpenLibraryScreen.kt`, which is already wired into the bottom navigation tab "Books" in `MainScreen.kt`.
- Bookmarks & Notes teammate: work entirely inside package `com.pes.studyresourcemanager.bookmarks`. Your entry point is `BookmarksScreen.kt`, which is already wired into the bottom navigation tab "Saved" in `MainScreen.kt`.

### 3. Git Branching Rules
- **Student 1 (Wikipedia):** branch `wikipedia`
- **Student 2 (Open Library):** branch `openlibrary`
- **Student 3 (Bookmarks & Notes):** branch `bookmarks`
- Pull latest `main` before starting work: `git pull origin main`
- Create feature branches from `main`. Do not push unfinished code directly to `main`.
