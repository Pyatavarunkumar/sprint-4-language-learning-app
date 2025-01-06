package com.example.learninglanguageapp2

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.languagelearningapp.ui.screens.LanguageSelectionScreen
import com.example.learninglanguageapp2.ui.screens.*

object Routes {
    const val SPLASH = "splash"
    const val REGISTRATION = "registration"
    const val LOGIN = "login"
    const val LANGUAGE_SELECTION = "language_selection"
    const val LESSONS = "language_lessons/{language}"
    const val LESSON_DETAILS = "lesson_details/{lessonId}/{language}"
    const val QUIZ = "quiz/{lessonId}"
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) { SplashScreen(navController) }
        composable(Routes.REGISTRATION) { RegistrationScreen(navController) }
        composable(Routes.LOGIN) { LoginScreen(navController) }
        composable(Routes.LANGUAGE_SELECTION) { LanguageSelectionScreen(navController) }
        composable(Routes.LESSONS) { backStackEntry ->
            val language = backStackEntry.arguments?.getString("language") ?: "Unknown"
            LanguageLessonsScreen(navController, navController.context, language)
        }
        composable(Routes.LESSON_DETAILS) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId")?.toIntOrNull() ?: -1
            val language = backStackEntry.arguments?.getString("language") ?: "Unknown"
            LessonDetailsScreen(navController, lessonId, navController.context, language)
        }
        composable(Routes.QUIZ) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId")?.toIntOrNull() ?: -1
            QuizScreen(navController, lessonId)
        }
    }
}


@Composable
fun MainActivityScreen() {
    val navController = rememberNavController()
    AppNavigation(navController = navController)
}
