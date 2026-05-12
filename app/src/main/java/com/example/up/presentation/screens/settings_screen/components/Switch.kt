package com.example.up.presentation.screens.settings_screen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.up.presentation.ui.theme.textDim

@Composable
fun Switch(
    modifier: Modifier = Modifier,
    value: Boolean,
    onValueChange: (Boolean) -> Unit
){
    Box(
        modifier = modifier
            .background(
                color = Color(0xffECEEF5),
                shape = RoundedCornerShape(50)
            )
            .defaultMinSize(
                minWidth = 38.dp,
                minHeight = 18.dp
            )
            .clickable(
                indication = null,
                interactionSource = null,
                onClick = {
                    onValueChange(!value)
                }
            )
    ){
        BoxWithConstraints(
            modifier = Modifier.matchParentSize()
        ) {
            val width = this.minWidth
            val height = this.minHeight

            val density = LocalDensity.current
            val widthPx = with(density) { width.toPx() }
            val heightPx = with(density) { height.toPx() }
            val radius = heightPx / 2.2f

            var target = if (value) widthPx - heightPx / 2 else heightPx / 2
            val position by animateFloatAsState(targetValue = target)

            Canvas(
                modifier = Modifier
            ) {
                drawCircle(
                    color = textDim,
                    radius = radius,
                    center = Offset(
                        x = position ,
                        y = heightPx / 2
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun SwitchTest(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column {
            var state1 by remember { mutableStateOf(false) }
            var state2 by remember { mutableStateOf(true) }

            Switch(
                value = state1
            ) { state1 = it}

            Switch(
                value = state2
            ) { state2 = it }
        }

    }
}