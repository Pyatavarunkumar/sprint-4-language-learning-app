package com.example.languagelearningapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learninglanguageapp2.Routes

@Composable
fun LanguageSelectionScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Select a Language", modifier = Modifier.padding(bottom = 16.dp))
        Button(onClick = { navController.navigate("language_lessons/Spanish") }) {
            Text(text = "Learn Spanish")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("language_lessons/French") }) {
            Text(text = "Learn French")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("language_lessons/German") }) {
            Text(text = "Learn German")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Back to Login")
        }

    }
}

