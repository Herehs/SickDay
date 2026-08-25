package com.example.up.presentation.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.common_сomponents.OnboardingBackground
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text

@Composable
fun Onboarding(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        OnboardingBackground()
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 35.dp)
                .padding(bottom = 35.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .align(alignment = Alignment.Start),
                text = "Добрый \nдень",
                fontSize = 40.sp,
                lineHeight = 50.sp,
                fontFamily = bodyFontFamily,
                color = text,
                fontWeight = FontWeight.W400,
                letterSpacing = -(0.8).sp
            )

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 30.dp)
                    .height(40.dp)
                    .width(330.dp)
                    .background(
                        color = Color(0xffD8DEFF),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xff97AAFE),
                        shape = RoundedCornerShape(9.dp)
                    )
                    .clickable(
                        onClick = onButtonClick,
                        indication = null,
                        interactionSource = null
                    ),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "Приступить к работе",
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    fontFamily = bodyFontFamily,
                    color = Color.Black,
                    fontWeight = FontWeight.W400,
                    letterSpacing = -(0.8).sp
                )
            }
        }
    }
}

@Preview
@Composable
fun OnboardingTest(){
    Onboarding( onButtonClick = {})
}