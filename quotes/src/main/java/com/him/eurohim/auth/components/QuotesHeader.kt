package com.him.eurohim.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.him.eurohim.auth.R

@Composable
fun QuotesHeader(
    onSubscribeToQuotes: () -> Unit,
    onLoadTopSecurities: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.real_time_quotes_title),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onSubscribeToQuotes) {
                Text(text = stringResource(id = R.string.real_time_quotes))
            }
            Button(onClick = onLoadTopSecurities) {
                Text(text = stringResource(id = R.string.top_30_stocks))
            }
        }
    }
}
