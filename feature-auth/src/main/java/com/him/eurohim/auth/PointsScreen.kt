package com.him.eurohim.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.him.eurohim.auth.views.PointsGraph
import com.him.eurohim.auth.views.PointsTable
import com.him.eurohim.domain.models.Point

@Composable
internal fun PointsScreenRoute(
    viewModel: PointsViewModel = hiltViewModel(),
) {
    PointsScreen(
        viewModel = viewModel
    )
}

@Composable
private fun PointsScreen(
    viewModel: PointsViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        when (uiState) {
            is UiState.Success , UiState.None -> {
                var points = emptyList<Point>()
                if(uiState is UiState.Success) {
                    points = (uiState as UiState.Success).points
                }
                var count by remember { mutableStateOf("") }

                Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    Text("Enter number of points:")
                    TextField(
                        value = count,
                        onValueChange = { count = it },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                    Button(
                        onClick = {
                            viewModel.fetchPoints(count.toIntOrNull() ?: 0)
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Go")
                    }

                    if (points.isNotEmpty()) {
                        PointsTable(points = points)
                        PointsGraph(points = points)
                    }
                }

            }

            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Error -> {
                val message = (uiState as UiState.Error).message
                Text("Error: $message")
            }
        }
    }
}
