package com.example.stravaapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stravaapp.data.models.ActivityStats

@Composable
fun HomeScreen(
    isAuthenticated: Boolean,
    athleteStats: ActivityStats?,
    onAuthClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isAuthenticated) {
            if (athleteStats != null) {
                DisplayActivityStats(activityStats = athleteStats)
            }
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

@Composable
fun DisplayActivityStats(activityStats: ActivityStats) {
    Column(modifier = Modifier.padding(16.dp)) {
        val biggestRideMiles = activityStats.biggest_ride_distance * 0.000621371
        val biggestClimbFeet = activityStats.biggest_climb_elevation_gain * 3.28084
        val totalDistanceMiles = activityStats.all_ride_totals.distance * 0.000621371
        val totalElevationGainFeet = activityStats.all_ride_totals.elevation_gain * 3.28084
        val totalRides = activityStats.all_ride_totals.count

        Text(text = "All Ride Totals:")
        Text(text = "  - Total Rides: $totalRides")
        Text(text = "  - Total Distance: ${"%.2f".format(totalDistanceMiles)} miles")
        Text(text = "  - Total Elevation Gain: ${"%.0f".format(totalElevationGainFeet)} feet")
        Text(text = "  - Biggest Ride Distance: ${"%.2f".format(biggestRideMiles)} miles")
        Text(text = "  - Biggest Climb Elevation Gain: ${"%.0f".format(biggestClimbFeet)} feet")
    }
}


