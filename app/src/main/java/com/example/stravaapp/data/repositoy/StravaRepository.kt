package com.example.stravaapp.data.repositoy

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class StravaRepository {

    private val client = OkHttpClient()

    // Function to get access token
    suspend fun getAccessToken(code: String): String = withContext(Dispatchers.IO) {
        Log.d("StravaRepository", "Starting request to get access token with code: $code")

        val requestBody = FormBody.Builder()
            .add("client_id", "136979")
            .add("client_secret", "d359f071c5a21ebe554c8ec03cc589ccbd8617af")
            .add("code", code)
            .add("grant_type", "authorization_code")
            .add("redirect_uri", "myapp://callback")
            .build()

        val request = Request.Builder()
            .url("https://www.strava.com/api/v3/oauth/token")
            .post(requestBody)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    Log.e("StravaRepository", "Request failed with code: ${response.code}")
                    throw IOException("Unexpected code $response")
                }

                val responseBody = response.body?.string()
                if (responseBody == null) {
                    Log.e("StravaRepository", "Empty response body")
                    throw IOException("Empty response body")
                }

                Log.d("StravaRepository", "Access token response: $responseBody")
                return@withContext responseBody
            }
        } catch (e: IOException) {
            Log.e("StravaRepository", "Error getting access token: ${e.message}")
            throw e
        }
    }
}
