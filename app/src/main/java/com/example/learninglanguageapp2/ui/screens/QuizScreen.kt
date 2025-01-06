package com.example.learninglanguageapp2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun QuizScreen(navController: NavController, lessonId: Int) {
    val questions = when (lessonId) {
        1 -> listOf(
            "What does 'Hello' mean in Spanish?" to listOf("Hola", "Adios", "Gracias"),
            "What does 'Goodbye' mean in Spanish?" to listOf("Adios", "Hola", "Gracias")
        )
        else -> emptyList()
    }

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }

    if (currentQuestionIndex < questions.size) {
        val (question, options) = questions[currentQuestionIndex]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = question, modifier = Modifier.padding(bottom = 16.dp))
            options.forEach { option ->
                Button(onClick = {
                    if (option == options[0]) score++
                    currentQuestionIndex++
                }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                    Text(text = option)
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Quiz Complete! Your score: $score/${questions.size}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigateUp() }) {
                Text(text = "Back to Lessons")
            }
        }
    }
}
