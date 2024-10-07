package com.example.stravaapp.data.network

import com.example.stravaapp.data.models.ActivityStats
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface StravaApiService {
    @GET("athletes/{id}/stats")
    fun getAthleteStats(
        @Path("id") id: Long,
        @Header("Authorization") auth: String
    ): Observable<ActivityStats>
}

