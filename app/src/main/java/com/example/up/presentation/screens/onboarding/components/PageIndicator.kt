package com.example.up.presentation.screens.onboarding.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageCount: Int = 0,
    currentPage: Int = 0
){
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount){ index ->
            val animatedColor by animateColorAsState(
                targetValue = if(index == currentPage) { Color(0xff000000) } else { Color(0xff7D7D7D) }
            )
            val animatedFloat by animateFloatAsState(
                targetValue = if(index == currentPage) { 2f } else { 1f }
            )
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(50))
                    .background(animatedColor)
                    .height(8.dp)
                    .aspectRatio(
                        animatedFloat / 1f
                    )
            )
        }
    }
}

@Preview
@Composable
fun Test(){
    var currentPage by rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PageIndicator(
            pageCount = 4,
            currentPage = currentPage
        )
        Button(
            onClick = { if(currentPage == 3) { currentPage = 0 } else currentPage++ }
        ){ }
    }
}