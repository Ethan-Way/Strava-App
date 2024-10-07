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
        Text(text = "Biggest Ride Distance: ${activityStats.biggest_ride_distance} m")
        Text(text = "Biggest Climb Elevation Gain: ${activityStats.biggest_climb_elevation_gain} m")

        // Display recent ride totals
        Text(text = "Recent Ride Totals:")
        Text(text = "Count: ${activityStats.recent_ride_totals.count}")
        Text(text = "Distance: ${activityStats.recent_ride_totals.distance} m")
        Text(text = "Moving Time: ${activityStats.recent_ride_totals.moving_time} s")
        // Add more fields as needed...

        // You can also add similar sections for all_run_totals, all_swim_totals, etc.
    }
}
