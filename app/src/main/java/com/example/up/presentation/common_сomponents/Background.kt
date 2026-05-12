package com.example.up.presentation.common_сomponents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Background(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Canvas(modifier = Modifier
            .matchParentSize()
            .blur(radius = 60.dp)
        ) {
            drawOval(
                color = Color(0xffD0EAFF),
                topLeft = Offset(
                    x = 0f,
                    y = -size.height * .1f
                ),
                size = Size(
                    width = size.width,
                    height = size.height * .25f
                )
            )
            drawOval(
                color = Color(0xffFFE1D0),
                topLeft = Offset(
                    x = -size.width * 0.25f,
                    y = size.height * 0.45f
                ),
                size = Size(
                    width = size.width * 0.5f,
                    height = size.height * 0.3f
                )
            )
            drawOval(
                color = Color(0xffFBFFDB),
                topLeft = Offset(
                    x = size.width * .6f,
                    y = size.height * .8f
                ),
                size = Size(
                    width = size.width * 0.5f,
                    height = size.height * 0.3f
                )
            )
        }
    }
}

@Composable
fun Background2(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Canvas(modifier = Modifier
            .matchParentSize()
            .blur(radius = 120.dp)
        ) {
            drawOval(
                color = Color(0xffD0EAFF),
                topLeft = Offset(
                    x = 0f,
                    y = -size.height * .1f
                ),
                size = Size(
                    width = size.width,
                    height = size.height * .25f
                )
            )
            drawOval(
                color = Color(0xffFFE1D0),
                topLeft = Offset(
                    x = -size.width * 0.25f,
                    y = size.height * 0.45f
                ),
                size = Size(
                    width = size.width * 0.5f,
                    height = size.height * 0.3f
                )
            )
            drawOval(
                color = Color(0xffFBFFDB),
                topLeft = Offset(
                    x = size.width * .6f,
                    y = size.height * .8f
                ),
                size = Size(
                    width = size.width * 0.5f,
                    height = size.height * 0.3f
                )
            )
            drawCircle(
                color = Color(0xff7792FF),
                radius = size.width * .4f,
                center = Offset(size.width / 2, size.height * .45f)
            )
        }
    }
}

@Composable
fun Background3(
    primaryColor: Color,
    secondaryColor: Color
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Canvas(modifier = Modifier
            .matchParentSize()
            .blur(radius = 140.dp)
        ) {
            drawCircle(
                center = Offset(size.width * .15f, size.height * .01f),
                radius = size.width / 2,
                color = primaryColor
            )

            drawCircle(
                center = Offset(size.width, size.height * .8f),
                radius = size.width / 2,
                color = secondaryColor
            )

        }
    }
}



@Preview(
    showBackground = true,
//    device = TABLET
)
@Composable
fun BackgroundTest(){
    Background3(
        primaryColor = Color(0xffFFA1CA).copy(alpha = .8f),
        secondaryColor = Color(0xffFFB3A1).copy(alpha = .5f)
    )
}