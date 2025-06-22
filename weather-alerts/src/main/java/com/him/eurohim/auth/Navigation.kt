package com.him.eurohim.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.him.eurohim.auth.alerts.AlertListScreenRoute
import com.him.eurohim.auth.alerts_details.AlertDetailsScreenRoute
import com.him.eurohim.domain.models.WeatherAlert

const val alertsScreenRoute = "alertsScreenRoute"
const val alertsDetailsScreenRoute = "alertsDetailsScreenRoute"
const val alertsDetailsArg = "alert"
fun NavGraphBuilder.alertsScreen(navController: NavController) {
    composable(route = alertsScreenRoute) {
        AlertListScreenRoute(navController = navController)
    }
}

fun NavGraphBuilder.alertsDetailsScreen(navController: NavController) {
    composable(route = alertsDetailsScreenRoute) {
        val alert = navController.previousBackStackEntry
            ?.savedStateHandle
            ?.get<WeatherAlert>(alertsDetailsArg)

        if (alert != null) {
            AlertDetailsScreenRoute(alert = alert)
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Alert not found")
            }
        }
    }
}