package com.example.learninglanguageapp2.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learninglanguageapp2.Routes

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login", modifier = Modifier.padding(bottom = 16.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (validateUser(navController.context, username, password)) {
                navController.navigate(Routes.LANGUAGE_SELECTION)
                    popUpTo(Routes.LOGIN) { var inclusive = true } // Remove Login Screen from back stack
            } else {
                Toast.makeText(navController.context, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Login")
        }
    }
}

fun popUpTo(s: String, function: () -> Unit) {
}

private fun validateUser(context: Context, username: String, password: String): Boolean {
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val savedUsername = sharedPreferences.getString("username", null)
    val savedPassword = sharedPreferences.getString("password", null)
    return username == savedUsername && password == savedPassword
}
