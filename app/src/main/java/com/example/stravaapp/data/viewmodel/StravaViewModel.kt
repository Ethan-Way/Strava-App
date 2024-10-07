package com.example.stravaapp.data.viewmodel

import androidx.lifecycle.ViewModel
import com.example.stravaapp.data.repositoy.StravaRepository

class StravaViewModel : ViewModel() {

    private val repository = StravaRepository()

    // Function to fetch access token
    suspend fun fetchAccessToken(code: String) {
        try {
            val accessTokenResponse = repository.getAccessToken(code)
            onAccessTokenReceived(accessTokenResponse)
        } catch (e: Exception) {
            onError("Error fetching access token: ${e.message}")
        }
    }

    private fun onAccessTokenReceived(response: String) {
    }

    fun onError(message: String) {
    }
}
