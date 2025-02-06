package com.him.eurohim.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun QuotesScreenRoute(
    viewModel: QuotesViewModel = hiltViewModel(),
) {
    QuotesScreen(
        viewModel = viewModel
    )
}

@Composable
fun QuotesScreen(viewModel: QuotesViewModel) {

    val state by viewModel.quotesState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
                Text("Loading quotes...")
            }
            state.error != null -> {
                Text("Error: ${state.error}", color = Color.Red)
            }
            state.quotes != null -> {
                Text("Real-time Quotes")
                Text("Ticker: ${state.quotes?.ticker}, Last Price: ${state.quotes?.ticker}")
            }
            else -> {
                Text("Waiting for data...")
            }
        }
    }
}
