package com.example.stravaapp.data.models

data class AccessTokenResult(
    val accessToken: String?,
    val athleteId: Long?
)

data class ActivityStats(
    val recent_run_totals: String,
    val all_run_totals: String,
    val recent_swim_totals: String,
    val biggest_ride_distance: Double,
    val ytd_swim_totals: String,
    val all_swim_totals: String,
    val recent_ride_totals: RecentRideTotals,
    val biggest_climb_elevation_gain: Double,
    val ytd_ride_totals: String,
    val all_ride_totals: String,
    val ytd_run_totals: String
)

data class RecentRideTotals(
    val distance: Double,
    val achievement_count: Int,
    val count: Int,
    val elapsed_time: Int,
    val elevation_gain: Double,
    val moving_time: Int
)

