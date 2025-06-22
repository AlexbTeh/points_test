package com.him.eurohim.auth.alerts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.him.eurohim.auth.alertsDetailsArg
import com.him.eurohim.auth.alertsDetailsScreenRoute
import com.him.eurohim.common.utils.DateUtils

@Composable
fun AlertListScreenRoute(navController: NavController) {
    AlertListScreen(viewModel = hiltViewModel(), navController = navController)
}

@Composable
fun AlertListScreen(viewModel: WeatherViewModel, navController: NavController) {
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        state.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${state.error}")
            }
        }
        else -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.alerts) { alert ->
                    ListItem(
                        headlineContent = { Text(alert.title) },
                        supportingContent = {
                            Column {
                                Text("${DateUtils.format(alert.startDate)} - ${DateUtils.format(alert.endDate)}")
                                Text(alert.source)
                            }
                        },
                        leadingContent = {
                            AsyncImage(
                                model = "https://picsum.photos/200",
                                contentDescription = null,
                                modifier = Modifier.size(64.dp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set(alertsDetailsArg, alert)
                                navController.navigate(alertsDetailsScreenRoute)
                            }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}




