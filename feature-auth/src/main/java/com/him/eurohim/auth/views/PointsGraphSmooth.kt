package com.him.eurohim.auth.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import com.him.eurohim.domain.models.Point

@Composable
fun PointsGraph(points: List<Point>, modifier: Modifier = Modifier) {
    val sortedPoints = points.sortedBy { it.x }

    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    val zoomModifier = Modifier.pointerInput(Unit) {
        detectTransformGestures { _, pan, zoom, _ ->
            scale *= zoom
            offsetX += pan.x
            offsetY += pan.y
        }
    }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .then(zoomModifier)
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                translationX = offsetX,
                translationY = offsetY
            )
            .background(Color.White)
    ) {

        if (sortedPoints.size > 1) {
            for (i in 0 until sortedPoints.size - 1) {
                val startPoint = sortedPoints[i]
                val endPoint = sortedPoints[i + 1]
                val startX = startPoint.x * 100
                val startY = startPoint.y * 100
                val endX = endPoint.x * 100
                val endY = endPoint.y * 100

                drawLine(
                    color = Color.Blue,
                    start = Offset(startX, startY),
                    end = Offset(endX, endY),
                    strokeWidth = 5f
                )
            }

            sortedPoints.forEach { point ->
                val canvasX = point.x * 100
                val canvasY = point.y * 100

                drawCircle(
                    color = Color.Red,
                    radius = 8f,
                    center = Offset(canvasX, canvasY)
                )
            }
        }
    }
}

