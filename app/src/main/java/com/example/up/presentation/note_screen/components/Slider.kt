package com.example.up.presentation.note_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit,
    value: Float,
    steps: Int,
    colors: SliderColors
){
    val anchoredDraggableState = AnchoredDraggableState(
        initialValue = value
    )


    Box(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ){
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .anchoredDraggable(
                    state = anchoredDraggableState,
                    orientation = Orientation.Horizontal
                )
        ){
            val width = size.width

            val anchors = DraggableAnchors {
                for (i in 0..steps) {
                    val fraction = i / steps.toFloat()
                    fraction at fraction * width
                }
            }
            anchoredDraggableState.updateAnchors(anchors)
            val offset = anchoredDraggableState.requireOffset()
            val currentValue = (offset / width).coerceIn(0f, 1f)
            onValueChange(currentValue)
            //scale
            drawRoundRect(
                size = Size(
                    width = size.width,
                    height = 20f
                ),
                color = colors.inactiveTrackColor,
                topLeft = Offset(
                    x = 0f,
                    y = (size.height - 20f) / 2
                ),
                cornerRadius = CornerRadius(16f, 16f)
            )
            //pointer
            drawCircle(
                radius = size.height / 4,
                color = colors.thumbColor,
                center = Offset(
                    x = offset,
                    y = size.height / 2
                )
            )
            drawRoundRect(
                size = Size(
                    width = value * size.width,
                    height = 20f
                ),
                color = colors.activeTrackColor,
                topLeft = Offset(
                    x = 0f,
                    y = (size.height - 20f) / 2
                ),
                cornerRadius = CornerRadius(16f, 16f)
            )
        }

    }
    LaunchedEffect(value) {
        val target = value.coerceIn(0f, 1f)
        anchoredDraggableState.animateTo(target)
    }
}



@Preview
@Composable
fun SliderTest(){
    var value = 0.5f
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CustomSlider(
            onValueChange = {value = it},
            value = value,
            steps = 10,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xff531111),
                activeTrackColor = Color(0xff531111),
                inactiveTrackColor = Color(0xff9C9C9C),
            )
        )
    }
}