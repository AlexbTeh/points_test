package com.him.eurohim.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.him.eurohim.auth.R
import com.him.eurohim.domain.models.Constants.LOGO_URL
import com.him.eurohim.domain.models.Quote
import com.him.eurohim.domain.roundToMinStep

@Composable
fun QuoteItem(quote: Quote) {

    val priceChangeColor = if ((quote.priceChange ?: 0.0) >= 0) Color.Green else Color.Red
    val logoUrl = "$LOGO_URL${quote.ticker.lowercase()}"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        AsyncImage(
            model = logoUrl,
            contentDescription = "${quote.ticker} Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, Color.LightGray, CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = quote.ticker, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = quote.name ?: stringResource(R.string.no_exchange_data),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "${stringResource(R.string.exchange)}: ${quote.exchange ?: stringResource(R.string.no_exchange_data)}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(1f)) {
            Text(
                text = "${stringResource(R.string.last_price)} ${quote.lastTradePrice?.roundToMinStep() ?: "—"}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "${stringResource(R.string.price_change)} ${quote.percentChange?.roundToMinStep() ?: "—"}%",
                color = priceChangeColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}




