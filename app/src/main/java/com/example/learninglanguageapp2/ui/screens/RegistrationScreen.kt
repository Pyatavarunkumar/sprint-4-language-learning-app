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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learninglanguageapp2.Routes

@Composable
fun RegistrationScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Register", modifier = Modifier.padding(bottom = 16.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
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
            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                saveUserToPreferences(navController.context, username, email, password)
                Toast.makeText(navController.context, "Registration Successful!", Toast.LENGTH_SHORT).show()
                navController.navigate(Routes.LOGIN)
            } else {
                Toast.makeText(navController.context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Register")
        }
    }
}

private fun saveUserToPreferences(context: Context, username: String, email: String, password: String) {
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    sharedPreferences.edit().apply {
        putString("username", username)
        putString("email", email)
        putString("password", password)
        apply()
    }
}
