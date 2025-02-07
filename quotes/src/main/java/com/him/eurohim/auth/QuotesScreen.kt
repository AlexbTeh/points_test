package com.him.eurohim.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.him.eurohim.auth.components.QuoteItem

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.real_time_quotes),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(stringResource(R.string.loading), modifier = Modifier.align(Alignment.CenterHorizontally), color = Color.Gray)
            }

            state.error != null -> {
                Text("${stringResource(R.string.error)} ${state.error}", color = Color.Red, style = MaterialTheme.typography.bodyMedium)
                Button(onClick = { viewModel.subscribeToQuotes() }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(stringResource(R.string.try_again))
                }
            }

            !state.quotes.isNullOrEmpty() -> {
                LazyColumn {
                    items(state.quotes!!, key = { it.ticker }) { quote ->
                        QuoteItem(quote)
                    }
                }
            }
            else -> {
                Text(stringResource(R.string.no_data), modifier = Modifier.align(Alignment.CenterHorizontally), color = Color.Gray)
            }
        }
    }
}




