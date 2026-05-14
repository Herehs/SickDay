package com.example.up.presentation.screens.calendar_screen.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt


@Composable
fun ChartComponent(
    modifier: Modifier = Modifier,
    date: LocalDate,
    lastUpdate: Int,
    healthScore: Int,
    data: List<Float>
){
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier,
                    text = "Индекс за " + date.format(DateTimeFormatter.ofPattern("dd MMMM")).replaceFirstChar { it.uppercase() },
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    fontFamily = bodyFontFamily,
                    color = text,
                    fontWeight = FontWeight.W400,
                    letterSpacing = -(0.8).sp
                )
                Text(
                    modifier = Modifier,
                    text = "Последнее обновление: $lastUpdate секунд назад",
                    fontSize = 10.sp,
                    lineHeight = 22.sp,
                    fontFamily = bodyFontFamily,
                    color = Color.Black.copy(alpha = .46f),
                    fontWeight = FontWeight.W400,
                    letterSpacing = -(0.8).sp
                )
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    modifier = Modifier,
                    text = "${data.last().roundToInt()}%",
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    fontFamily = bodyFontFamily,
                    color = Color(0xffFF5D5D),
                    fontWeight = FontWeight.W400,
                    letterSpacing = -(0.8).sp
                )
                Text(
                    modifier = Modifier,
                    text = "Индекс опасности",
                    fontSize = 10.sp,
                    lineHeight = 22.sp,
                    fontFamily = bodyFontFamily,
                    color = Color.Black.copy(alpha = .46f),
                    fontWeight = FontWeight.W400,
                    letterSpacing = -(0.8).sp
                )
            }
        }
        val animatedData = data.map { value ->

            val animatedValue: Float by animateFloatAsState(
                targetValue = value,
                animationSpec = tween(
                    durationMillis = 700,
                    easing = FastOutSlowInEasing
                ),
                label = ""
            )

            animatedValue
        }
        LineChart(Modifier.padding(top = 14.dp), data = animatedData, max = data.maxOrNull() ?: 1f)
    }
}

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    data: List<Float>,
    max: Float
) {

    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(135.dp)
    ) {
        val width = size.width
        val height = size.height

        val maxValue = max
        val minValue = data.minOrNull() ?: 0f

        val range = (maxValue - minValue).takeIf { it != 0f } ?: 1f

        val stepX = width / (data.size - 1)

        val centerY = height / 2f

        val points = data.mapIndexed { index, value ->
            val x = index * stepX
            val y = height - ((value - minValue) / range) * height
            Offset(x, y)
        }

        val linePath = Path().apply {
            moveTo(points.first().x, points.first().y)
            points.drop(1).forEach {
                lineTo(it.x, it.y)
            }
        }

        drawPath(
            path = linePath,
            color = Color(0xffFF5D5D),
            style = Stroke(width = 4f)
        )

        val fillPath = Path().apply {
            moveTo(points.first().x, height)
            lineTo(points.first().x, points.first().y)

            for (i in 1 until points.size) {
                lineTo(points[i].x, points[i].y)
            }

            lineTo(points.last().x, height)
            close()
        }

        val gradientBrush = Brush.verticalGradient(
            colors = listOf(
                Color(0xffFF5D5D).copy(alpha = .5f),
                Color.Transparent
            )
        )

        drawPath(
            path = fillPath,
            brush = gradientBrush
        )

        drawLine(
            color = Color(0xffFF5D5D).copy(.22f),
            start = Offset(0f, centerY),
            end = Offset(width, centerY),
            strokeWidth = 2f,
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(10f, 10f),
                0f
            )
        )
    }
}

@Preview
@Composable
fun ChartComponentTest(){
    ChartComponent(
        date = LocalDate.now(),
        lastUpdate = 67,
        healthScore = 67,
        data = listOf(0f, 35f, 70f, 70f, 100f)
    )
}
