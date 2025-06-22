package com.him.eurohim.auth.alerts_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.him.eurohim.common.utils.DateUtils
import com.him.eurohim.domain.models.WeatherAlert

@Composable
internal fun AlertDetailsScreenRoute(alert: WeatherAlert) {
    AlertDetailsScreen(alert = alert)
}

@Composable
fun AlertDetailsScreen(alert: WeatherAlert) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        AsyncImage(
            model = "https://picsum.photos/400",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(alert.title, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text("From: ${DateUtils.format(alert.startDate)}")
        Text("To: ${DateUtils.format(alert.endDate)}")
        Spacer(modifier = Modifier.height(4.dp))
        Text("Severity: ${alert.severity}")
        Text("Urgency: ${alert.urgency}")
        Text("Certainty: ${alert.certainty}")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Source: ${alert.source}")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Description:", style = MaterialTheme.typography.titleMedium)
        Text(alert.description)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Zones:", style = MaterialTheme.typography.titleMedium)
        alert.affectedZones.forEach {
            Text("• ${it.name}")
        }
    }
}
