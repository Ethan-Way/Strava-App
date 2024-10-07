package com.example.stravaapp.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.stravaapp.data.repositoy.StravaRepository

class StravaViewModel : ViewModel() {

    private val repository = StravaRepository()

    // Function to fetch access token
    suspend fun fetchAccessToken(code: String) {
        try {
            Log.d("StravaViewModel", "Fetching access token for code: $code")
            val accessTokenResponse = repository.getAccessToken(code)
            onAccessTokenReceived(accessTokenResponse)
        } catch (e: Exception) {
            onError("Error fetching access token: ${e.message}")
        }
    }

    private fun onAccessTokenReceived(response: String) {
        Log.d("StravaViewModel", "Access token received: $response")
    }

    fun onError(message: String) {
        Log.e("StravaViewModel", message)
    }
}
