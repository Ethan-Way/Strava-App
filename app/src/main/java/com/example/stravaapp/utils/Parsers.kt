package com.example.stravaapp.utils

import android.util.Log
import com.example.stravaapp.data.models.AccessTokenResult
import org.json.JSONObject

fun parseAccessToken(response: String): AccessTokenResult {
    return try {
        val jsonObject = JSONObject(response)
        val accessToken = jsonObject.getString("access_token")
        val athleteId = jsonObject.getJSONObject("athlete").getLong("id")

        AccessTokenResult(accessToken, athleteId)
    } catch (e: Exception) {
        Log.e("parseAccessToken", "Error parsing access token: ${e.message}")
        AccessTokenResult(null, null)
    }
}
