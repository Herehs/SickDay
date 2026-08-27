package com.example.up.presentation.common_сomponents

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.up.R
import kotlin.random.Random

val figureColors = listOf(
    Color(0xFFFF91ED),
    Color(0xFFD0EAFF),
    Color(0xFFFFE1D0),
    Color(0xFFFBFFDB),
    Color(0xFFFFD0EC),
    Color(0xFFE0D0FF),
    Color(0xFFD0FFE6),
    Color(0xFFFFC6FF),
    Color(0xFFBDB2FF),
    Color(0xFFA0C4FF),
    Color(0xFFCAFFBF)

)


private data class MovingOvalState(
    val animatable: Animatable<Offset, *>,
    val color: Color
)

@Composable
fun Background() {
    val movingCount = 5

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val widthPx = with(LocalDensity.current) { maxWidth.toPx() }
        val heightPx = with(LocalDensity.current) { maxHeight.toPx() }

        val ovalStates = remember(widthPx, heightPx) {
            List(movingCount) {
                MovingOvalState(
                    animatable = Animatable(
                        Offset(
                            x = Random.nextFloat() * widthPx,
                            y = Random.nextFloat() * (heightPx * 0.8f)
                        ),
                        Offset.VectorConverter
                    ),
                    color = figureColors.random()
                )
            }
        }

        ovalStates.forEach { state ->
            LaunchedEffect(state.animatable, widthPx, heightPx) {
                if (widthPx == 0f || heightPx == 0f) return@LaunchedEffect

                while (true) {
                    val startFromLeft = Random.nextBoolean()
                    val targetX = if (startFromLeft) widthPx else -widthPx * 0.5f
                    val targetY = Random.nextFloat() * (heightPx * 0.8f)
                    val duration = Random.nextInt(5000, 10000)

                    state.animatable.animateTo(
                        targetValue = Offset(targetX, targetY),
                        animationSpec = tween(
                            durationMillis = duration,
                            easing = LinearEasing
                        )
                    )
                }
            }
        }

        Canvas(
            modifier = Modifier
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

            ovalStates.forEach { state ->
                drawOval(
                    color = state.color,
                    topLeft = state.animatable.value,
                    size = Size(
                        width = size.width * 0.5f,
                        height = size.height * 0.3f
                    )
                )
            }
        }
    }
}
@Composable
fun OnboardingBackground(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        val icon = ImageBitmap.imageResource(id = R.drawable.onboarding)


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
        Canvas(modifier = Modifier
            .matchParentSize()
            .graphicsLayer {
                scaleX = 3f
                scaleY = 3f
            }
        ){
            drawImage(
                image = icon,
                topLeft = Offset(
                    (size.width - icon.width) * .5f,
                    (size.height - icon.height) * .49f
                ),

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
fun OnboardingBackgroundTest(){
    Background()
}