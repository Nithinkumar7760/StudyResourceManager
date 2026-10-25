# Study Resource Manager

A modern Android application built with Kotlin and Jetpack Compose designed to help students discover, search, and manage educational study materials from various open sources.

Developed as a college mini project by a team of 3 students.

---

## Architecture & Project Structure

The project is split into three independent modules/pieces owned by each teammate:

1. **Wikipedia Piece** *(Owner: Student 1)*
   - Educational topic search using the Wikipedia Query API.
   - Article summary inspection with thumbnails and key extracts via the Wikipedia REST API.
   - Direct launch of complete articles in the browser.

2. **Open Library Piece** *(Owner: Student 2)*
   - Book catalog search by title, author, or subject.
   - Display book details with book cover art and publish metadata.
   - *Status: Skeleton / Placeholder ready for implementation in package `openlibrary`.*

3. **Bookmarks & Notes Piece** *(Owner: Student 3)*
   - Local database persistence using Room.
   - Bookmark educational resources across Wikipedia and Open Library.
   - Add, edit, and organize custom study notes.
   - *Status: Skeleton / Placeholder ready for implementation in package `bookmarks`.*

---

## Tech Stack

- **Language:** Kotlin 2.2.x
- **UI Toolkit:** Jetpack Compose (Material 3 default clean aesthetic)
- **Architecture:** MVVM (Model-View-ViewModel) with Unidirectional Data Flow (StateFlow)
- **Networking:** Retrofit 2 + Gson Converter
- **Image Loading:** Coil for Compose
- **Navigation:** Jetpack Navigation Compose
- **Min SDK:** 24 (Android 7.0 Nougat)
- **Target / Compile SDK:** 37

---

## Folder Structure

```text
com.pes.studyresourcemanager/
│
├── model/                     # Shared data models
│   └── Resource.kt            # Unified model used across all features
│
├── navigation/                # Central navigation & app shell
│   ├── Screen.kt              # Route definitions & bottom nav destinations
│   └── MainScreen.kt          # Bottom navigation bar & NavHost container
│
├── wikipedia/                 # Wikipedia feature package
│   ├── api/                   # Retrofit service and API response DTOs
│   ├── data/                  # Repository and data mapping to Resource
│   ├── ui/                    # Compose screens, components, and ViewModel
│   └── WikipediaScreen.kt     # Entry screen for the Wikipedia tab
│
├── openlibrary/               # Open Library feature package (Teammate)
│   └── OpenLibraryScreen.kt   # Screen composable (plugs directly into NavHost)
│
├── bookmarks/                 # Bookmarks & Notes feature package (Teammate)
│   └── BookmarksScreen.kt     # Screen composable (plugs directly into NavHost)
│
├── ui/theme/                  # App theme, typography, and color palette
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
│
└── MainActivity.kt            # App launch activity
```

---

## How to Clone and Run

### Prerequisites
- Android Studio Ladybug / Meerkat / newer
- JDK 17, 21, or 25 (Android Studio bundled JBR works out of the box)
- Android device or emulator running API 24 or higher

### Steps
1. **Clone the repository:**
   ```bash
   git clone https://github.com/Nithinkumar7760/StudyResourceManager.git
   cd StudyResourceManager
   ```

2. **Open in Android Studio:**
   - Launch Android Studio.
   - Select **File > Open...** and choose the `StudyResourceManager` directory.
   - Wait for Gradle sync to finish.

3. **Run from Terminal or Studio:**
   - In Android Studio: Click the green **Run** button (`Shift + F10`).
   - Via Gradle CLI:
     ```bash
     ./gradlew assembleDebug
     ```
     The output APK will be generated at `app/build/outputs/apk/debug/app-debug.apk`.

---

## Team Workflow & Git Instructions

1. **Branches:**
   - `main`: Stable base containing app skeleton, shared models, and documentation.
   - `wikipedia`: Development branch for the Wikipedia feature.
   - `openlibrary`: Development branch for Open Library books search.
   - `bookmarks`: Development branch for Room database and notes.

2. **Rules for Teammates:**
   - Work strictly within your assigned package (`openlibrary` or `bookmarks`).
   - Map your external results to `com.pes.studyresourcemanager.model.Resource`.
   - Never commit sensitive keys or `.idea/` / `local.properties` files.
