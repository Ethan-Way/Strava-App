package com.example.stravaapp.data.repositoy

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

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            return@withContext response.body?.string() ?: throw IOException("Empty response body")
        }
    }
}
