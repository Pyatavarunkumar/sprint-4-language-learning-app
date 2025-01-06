package com.example.learninglanguageapp2.ui.screens

import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.*

@Composable
fun LessonDetailsScreen(
    navController: NavController,
    lessonId: Int,
    context: Context,
    language: String
) {
    val lessonContent = when (lessonId) {
        1 -> listOf("Hello", "Goodbye", "Thank you")
        2 -> listOf("Good morning", "Good evening", "How are you?")
        3 -> listOf("One", "Two", "Three")
        else -> emptyList()
    }

    var tts by remember { mutableStateOf<TextToSpeech?>(null) }
    var ttsError by remember { mutableStateOf(false) }

    // Initialize TextToSpeech
    DisposableEffect(Unit) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.ERROR) {
                ttsError = true
            } else {
                tts?.language = when (language.lowercase(Locale.ROOT)) {
                    "spanish" -> Locale("es", "ES")
                    "french" -> Locale("fr", "FR")
                    else -> Locale.ENGLISH
                }
            }
        }
        onDispose {
            tts?.shutdown()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Lesson $lessonId - $language", modifier = Modifier.padding(bottom = 8.dp))

        if (lessonContent.isEmpty()) {
            Text(text = "No content available for this lesson.")
        } else {
            lessonContent.forEach { phrase ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = phrase, modifier = Modifier.weight(1f))
                    Button(onClick = {
                        if (ttsError || tts == null) {
                            Toast.makeText(context, "Audio playback is not available.", Toast.LENGTH_SHORT).show()
                        } else {
                            tts?.speak(phrase, TextToSpeech.QUEUE_FLUSH, null, null)
                        }
                    }) {
                        Text(text = "Play Audio")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Start Quiz Button
        Button(onClick = {
            navController.navigate("quiz/$lessonId")
        }) {
            Text(text = "Start Quiz")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Back to Lessons Button
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Back to Lessons")
        }
    }
}
