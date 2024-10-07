package com.example.stravaapp.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stravaapp.data.models.ActivityStats
import com.example.stravaapp.data.network.StravaApiService
import com.example.stravaapp.data.repositoy.StravaRepository
import com.example.stravaapp.utils.parseAccessToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class StravaViewModel : ViewModel() {

    private val repository = StravaRepository()
    private var accessToken: String? = null

    // LiveData for authentication status
    private val _isAuthenticated = MutableLiveData<Boolean>(false)
    val isAuthenticated: LiveData<Boolean> get() = _isAuthenticated

    // LiveData for athlete stats
    private val _athleteStats = MutableLiveData<ActivityStats?>(null)
    val athleteStats: LiveData<ActivityStats?> = _athleteStats

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

        val result = parseAccessToken(response)
        accessToken = result.accessToken
        val athleteId = result.athleteId

        if (accessToken != null) {
            // Update authentication status
            _isAuthenticated.postValue(true) // Set to true on successful authentication
            viewModelScope.launch {
                val stats = athleteId?.let { repository.getAthleteStats(it, "Bearer $accessToken") }
                _athleteStats.postValue(stats)
            }
        }
    }

    fun onError(message: String) {
        Log.e("StravaViewModel", message)
    }


}
