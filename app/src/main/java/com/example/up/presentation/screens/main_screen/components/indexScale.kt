package com.example.up.presentation.screens.main_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.ui.theme.bodyFontFamily
import kotlin.math.roundToInt

@Composable
fun IndexScale(
    modifier: Modifier = Modifier,
    text: String = "",
    value: Float = 1f
){
    Column(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = text,
            fontSize = 17.sp,
            lineHeight = 22.sp,
            fontFamily = bodyFontFamily,
            color = com.example.up.presentation.ui.theme.text,
            fontWeight = FontWeight.W400
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Canvas(
                modifier = Modifier.weight(1f).height(20.dp)
            ) {
                val gradient = Brush.horizontalGradient(
                    colorStops = arrayOf(
                        0f to Color(0xffDEFFCB),
                        .4f to Color(0xffFFEFCE),
                        1f to Color(0xffFFD4D4)
                    ),
                )
                //scale
                drawRoundRect(
                    size = Size(
                        width = size.width,
                        height = 20f
                    ),
                    brush = gradient,
                    topLeft = Offset(
                        x = 0f,
                        y = (size.height - 20f) / 2
                    ),
                    cornerRadius = CornerRadius(16f, 16f)
                )
                //pointer
                drawRect(
                    size = Size(
                        width = 8f,
                        height = size.height
                    ),
                    color = Color.Black,
                    topLeft = Offset(
                        x = value * size.width,
                        y = 0f
                    )
                )
            }
            Text(
                modifier = Modifier.width(64.dp),
                text = "${
                    if (value in 0f..1f){
                        value.times(100).roundToInt()
                    } else 0
                }%",
                textAlign = TextAlign.Right,
                fontSize = 24.sp,
                lineHeight = 22.sp,
                fontFamily = bodyFontFamily,
                color = com.example.up.presentation.ui.theme.text,
                fontWeight = FontWeight.W400
            )
        }
    }
}

@Preview(
    showBackground = true,
//    device = PIXEL
)
@Composable
fun IndexScaleTest(){
    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.Center,
    ){
        IndexScale(value = 0f, text = "penis")
        Spacer(modifier = Modifier.height(10.dp))
        IndexScale(value = .67f, text = "zalupa")
        Spacer(modifier = Modifier.height(10.dp))
        IndexScale(value = 1f, text = "pizda")
    }
}