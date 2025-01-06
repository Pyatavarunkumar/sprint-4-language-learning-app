package com.example.learninglanguageapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.learninglanguageapp2.ui.theme.LearningLanguageApp2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Initialize NavController for navigation between screens
            val navController = rememberNavController()

            // Apply the app's theme and navigation graph
            LearningLanguageApp2Theme {
                AppNavigation(navController = navController)
            }
        }
    }
}

// A simple composable for UI testing purposes
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearningLanguageApp2Theme {
        Text("Hello, Language Learning App!") // Replace Greeting with a simpler preview
    }
}
