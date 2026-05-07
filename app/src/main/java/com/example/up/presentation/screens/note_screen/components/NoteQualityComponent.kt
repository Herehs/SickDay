package com.example.up.presentation.screens.note_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.R
import com.example.up.presentation.common_сomponents.Background
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.openedQualityComponent
import com.example.up.presentation.ui.theme.texDark

@Composable
fun NoteQualityComponent(
    modifier: Modifier = Modifier,
    label: String
){
    var opened by rememberSaveable { mutableStateOf(false) }
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    val animatedColor by animateColorAsState(
        if (opened) openedQualityComponent.copy(alpha = .4f) else Color(0xffFFFFFF),
        label = "color"
    )


    Column(
        modifier = modifier
            .padding(top = 4.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = animatedColor)
            .animateContentSize(
                animationSpec = spring(
                    stiffness = Spring.StiffnessHigh,
                    visibilityThreshold = IntSize.Zero,
                )
            )
            .border(
                width = 1.dp,
                color = Color(0xff9F8A8F).copy(alpha = .2f),
                shape = RoundedCornerShape(5.dp)
            )
            .padding(horizontal = 18.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp, bottom = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.open_eye),
                    contentDescription = null,
                    modifier = Modifier.height(14.dp),
                    tint = Color(0xff6C706D)
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = label,
                    fontSize = 17.sp,
                    lineHeight = 22.sp,
                    fontFamily = bodyFontFamily,
                    color = texDark,
                    fontWeight = FontWeight.W400,
                    letterSpacing = -(0.8).sp
                )
            }
            Icon(
                painter = painterResource(
                    if(opened) R.drawable.minus else R.drawable.plus
                ),
                contentDescription = null,
                modifier = Modifier
                    .height(14.dp)
                    .padding(end = 14.dp)
                    .clickable(
                        interactionSource = null,
                        onClick = {opened = !opened}
                    ),
                tint = Color(0xff6C706D)
            )
        }
        AnimatedVisibility(
            visible = opened
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = 40.dp, top = 20.dp)
            ){

                CustomSlider(
                    onValueChange = {sliderPosition = it},
                    value = sliderPosition,
                    steps = 5,
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xff531111),
                        activeTrackColor = Color(0xff531111),
                        inactiveTrackColor = Color(0xff9C9C9C),
                    )
                )
            }

        }



    }
}


@Preview
@Composable
fun NoteQualityComponentTest(){
    Scaffold {
        paddingValues ->
        Background()
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
        ){
            item{
              NoteQualityComponent(
                  label = "Сонливость"
              )
            }
            item {
                NoteQualityComponent(
                    label = "Давление"
                )
            }
            item {
                NoteQualityComponent(
                    label = "Слабость"
                )
            }
            item {
                NoteQualityComponent(
                    label = "Диарея"
                )
            }



        }

    }
}
