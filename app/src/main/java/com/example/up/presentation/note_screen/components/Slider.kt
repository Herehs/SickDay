package com.example.up.presentation.note_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.up.presentation.ui.theme.text
import kotlin.math.roundToInt

@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit,
    value: Float,
    steps: Int,
    colors: SliderColors
){
    BoxWithConstraints(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ){
        val width = this.minWidth
        val height = this.minHeight

        val state = rememberSaveable(saver = AnchoredDraggableState.Saver()) {
            AnchoredDraggableState(initialValue = value)
        }

        val density = LocalDensity.current
        val widthPx = with(density) { width.toPx() }
        val heightPx = with(density) { height.toPx() }

        val thumbSize = 24.dp
        val thumbRadiusPx = with(LocalDensity.current) { thumbSize.toPx() / 2 }

        val anchors = DraggableAnchors {
            for (i in 0..steps) {
                val fraction = i / steps.toFloat()
                fraction at fraction * widthPx
            }
        }

        state.updateAnchors(anchors)

        Box(
            modifier = Modifier
                .zIndex(2f)
                .offset{
                    IntOffset(
                        x = (state.requireOffset() - thumbRadiusPx).roundToInt(),
                        y = (heightPx / 2f - thumbRadiusPx).roundToInt()
                    )
                }
                .anchoredDraggable(
                    state,
                    Orientation.Horizontal
                )
                .size(thumbSize)
        ){
            Canvas(
                modifier = Modifier.matchParentSize()
            ) {
                drawCircle(
                    color = colors.thumbColor
                )

            }
        }

        Box(
            modifier = Modifier.matchParentSize()
        ){
            Canvas(
                modifier = Modifier.matchParentSize()
            ) {
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
                drawRoundRect(
                    size = Size(
                        width = state.requireOffset(),
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
            state.animateTo(value.coerceIn(0f, 1f))
        }

        LaunchedEffect(state.requireOffset()) {
            val fraction = (state.requireOffset() / widthPx)
                .coerceIn(0f, 1f)

            onValueChange(fraction)
        }
    }
}



@Preview
@Composable
fun SliderTest(){
    var value by remember { mutableStateOf(0f) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = value.toString()
        )

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