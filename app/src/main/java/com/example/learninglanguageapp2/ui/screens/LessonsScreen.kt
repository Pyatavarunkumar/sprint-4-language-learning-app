package com.example.learninglanguageapp2.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learninglanguageapp2.Routes

@Composable
fun LessonsScreen(navController: NavController, language: String) {
    val sharedPreferences = navController.context.getSharedPreferences("ProgressPrefs", Context.MODE_PRIVATE)
    val completedLessons = remember { mutableStateOf(sharedPreferences.getInt("${language}_completed", 0)) }
    val totalLessons = 5 // Fixed number of lessons

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Lessons for $language", modifier = Modifier.padding(bottom = 16.dp))
        for (i in 1..totalLessons) {
            LessonItem(
                lessonNumber = i,
                isUnlocked = i <= completedLessons.value + 1,
                isCompleted = i <= completedLessons.value,
                onComplete = {
                    sharedPreferences.edit().putInt("${language}_completed", i).apply()
                    completedLessons.value = i
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Progress: ${(completedLessons.value * 100) / totalLessons}%", modifier = Modifier.padding(top = 16.dp))
    }
}

@Composable
fun LessonItem(lessonNumber: Int, isUnlocked: Boolean, isCompleted: Boolean, onComplete: () -> Unit) {
    if (isUnlocked) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Lesson $lessonNumber")
            if (isCompleted) {
                Text(text = "Completed")
            } else {
                Button(onClick = onComplete) {
                    Text(text = "Complete")
                }
            }
        }
    } else {
        Text(text = "Lesson $lessonNumber (Locked)", modifier = Modifier.padding(8.dp))
    }
}
