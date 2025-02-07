package com.him.eurohim.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val quotesScreenRoute = "quotesScreenRoute"

fun NavGraphBuilder.quotesScreen(){
    composable(route = quotesScreenRoute){
        QuotesScreenRoute()
    }
}