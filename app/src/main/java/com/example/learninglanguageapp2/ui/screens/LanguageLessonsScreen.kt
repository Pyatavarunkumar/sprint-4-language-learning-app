package com.example.learninglanguageapp2.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learninglanguageapp2.Routes

data class Lesson(val id: Int, val title: String, val description: String)

@Composable
fun LanguageLessonsScreen(navController: NavController, context: Context, language: String) {
    val lessons = listOf(
        Lesson(1, "Introduction", "Learn basic phrases to start."),
        Lesson(2, "Greetings", "Learn how to greet people."),
        Lesson(3, "Numbers", "Learn how to count.")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "$language Lessons", modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn {
            items(lessons) { lesson ->
                Button(
                    onClick = {
                        navController.navigate("lesson_details/${lesson.id}/$language")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Column {
                        Text(text = lesson.title)
                        Text(text = lesson.description)
                    }
                }
            }
        }
    }
}
