//package com.example.stravaapp.ui
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.stravaapp.data.models.AthleteStats
//
//@Composable
//fun AthleteStatsScreen(athleteStats: AthleteStats?) {
//    athleteStats?.let {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = "Biggest Ride Distance: ${it.biggestRideDistance} meters")
//            Text(text = "Biggest Climb Elevation Gain: ${it.biggestClimbElevationGain} meters")
//            Text(text = "Recent Rides: ${it.recentRideTotals.count} rides")
//            Text(text = "Total Distance: ${it.allRideTotals.distance} meters")
//        }
//    } ?: run {
//        Text(text = "No stats available.")
//    }
//}
