package com.him.eurohim.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val pointsScreenRoute = "pointsScreenRoute"

fun NavGraphBuilder.pointsScreen(){
    composable(route = pointsScreenRoute){
        PointsScreenRoute()
    }
}