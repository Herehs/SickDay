package com.example.up.presentation.note_screen.components

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlin.math.roundToInt

@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit,
    value: Float,
    steps: Int,
    colors: SliderColors,
    showNumbers: Boolean = true
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

        val thumbSize = 15.dp
        val thumbRadiusPx = with(LocalDensity.current) { thumbSize.toPx() / 2 }

        val anchors = DraggableAnchors {
            for (i in 0 until steps) {
                val fraction = i / (steps - 1).toFloat()
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
                        height = 15f
                    ),
                    color = colors.inactiveTrackColor,
                    topLeft = Offset(
                        x = 0f,
                        y = (size.height - 15f) / 2
                    ),
                    cornerRadius = CornerRadius(8f, 8f)
                )
                drawRoundRect(
                    size = Size(
                        width = state.requireOffset(),
                        height = 15f
                    ),
                    color = colors.activeTrackColor,
                    topLeft = Offset(
                        x = 0f,
                        y = (size.height - 15f) / 2
                    ),
                    cornerRadius = CornerRadius(8f, 8f)
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
        if(showNumbers){
            Box(
                modifier = Modifier
                    .matchParentSize()
            ) {
                for (i in 0 until steps) {
                    val fraction = i / (steps - 1).toFloat()
                    val xPx = fraction * widthPx

                    var textWidthPx by remember { mutableStateOf(0) }

                    Text(
                        text = (i + 1).toString(),
                        onTextLayout = {
                            textWidthPx = it.size.width
                        },
                        modifier = Modifier.offset {
                            IntOffset(
                                x = (xPx - textWidthPx / 2f).roundToInt(),
                                y = heightPx.roundToInt() - 25
                            )
                        },
                        fontSize = 12.sp,
                        lineHeight = 22.sp,
                        letterSpacing = -(0.8).sp,
                        color = Color(0xff848484)
                    )
                }
            }
        }
    }
}



@Preview
@Composable
fun SliderTest(){
    var value by remember { mutableStateOf(0f) }
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center
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

        var value2 by remember { mutableStateOf(0f) }

        CustomSlider(
            onValueChange = {value2 = it},
            value = value2,
            steps = 5,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xff531111),
                activeTrackColor = Color(0xff531111),
                inactiveTrackColor = Color(0xff9C9C9C),
            )
        )

    }
}