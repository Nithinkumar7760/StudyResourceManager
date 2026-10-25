package com.pes.studyresourcemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pes.studyresourcemanager.navigation.MainScreen
import com.pes.studyresourcemanager.ui.theme.StudyResourceManagerTheme

/**
 * Entry activity for the Study Resource Manager application.
 * Initializes edge-to-edge display and hosts the main application composable.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyResourceManagerTheme {
                MainScreen()
            }
        }
    }
}