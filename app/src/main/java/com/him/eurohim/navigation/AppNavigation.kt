package com.him.eurohim.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.him.eurohim.auth.alertsDetailsScreen
import com.him.eurohim.auth.alertsScreen
import com.him.eurohim.auth.alertsScreenRoute


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = alertsScreenRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        alertsScreen(navController)
        alertsDetailsScreen(navController)
    }
}