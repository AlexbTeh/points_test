package com.him.eurohim.auth.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.him.eurohim.domain.models.Point

@Composable
fun PointsTable(points: List<Point>) {
    LazyColumn {
        items(points) { point ->
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Text(text = "X: ${point.x}", modifier = Modifier.weight(1f))
                Text(text = "Y: ${point.y}", modifier = Modifier.weight(1f))
            }
        }
    }
}