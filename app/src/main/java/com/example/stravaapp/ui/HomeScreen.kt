package com.example.stravaapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    isAuthenticated: Boolean,
    onAuthClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isAuthenticated) {
            Text(text = "Welcome")
        } else {
            StravaAuthButton(onClick = onAuthClick)
        }
    }
}

@Composable
fun StravaAuthButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Authorize with Strava")
    }
}
