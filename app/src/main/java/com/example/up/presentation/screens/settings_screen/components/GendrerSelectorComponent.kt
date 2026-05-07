package com.example.up.presentation.screens.settings_screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.domain.model.Gender
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text
import com.example.up.presentation.ui.theme.textDim


@Composable
fun GenderSelectorComponent(
    modifier: Modifier = Modifier,
    onClick: (Gender) -> Unit,
    value: Gender = Gender.FEMALE
){
    val maleBackgroundColor by animateColorAsState(
        targetValue = if (value == Gender.MALE) {
            Color.White
        } else {
            Color(0xffF2F2F2).copy(alpha = .72f)
        }
    )
    val maleTextColor by animateColorAsState(
        targetValue = if (value == Gender.MALE) {
            text
        } else {
            textDim
        }
    )

    val femaleBackgroundColor by animateColorAsState(
        targetValue = if (value == Gender.FEMALE) {
            Color.White
        } else {
            Color(0xffF2F2F2).copy(alpha = .72f)
        }
    )
    val femaleTextColor by animateColorAsState(
        targetValue = if (value == Gender.FEMALE) {
            text
        } else {
            textDim
        }
    )



    Row(
        modifier = modifier
            .height(34.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(topStart = 7.dp, bottomStart = 7.dp)
                )
                .padding(1.dp)
                .background(
                    color = maleBackgroundColor,
                    shape = RoundedCornerShape(topStart = 6.dp, bottomStart = 6.dp)
                )
                .clickable(
                    indication = null,
                    interactionSource = null
                ){
                    onClick(Gender.MALE)
                }
                .padding(end = 2.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier.padding(horizontal = 40.dp),
                text = Gender.MALE.text,
                fontSize = 14.sp,
                lineHeight = 22.sp,
                fontFamily = bodyFontFamily,
                color = maleTextColor,
                fontWeight = FontWeight.W400,
                letterSpacing = -(0.8).sp
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(topEnd = 7.dp, bottomEnd = 7.dp)
                )
                .padding(1.dp)
                .background(
                    color = femaleBackgroundColor
                )
                .clickable(
                    indication = null,
                    interactionSource = null
                ){
                    onClick(Gender.FEMALE)
                },
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier.padding(horizontal = 40.dp),
                text = Gender.FEMALE.text,
                fontSize = 14.sp,
                lineHeight = 22.sp,
                fontFamily = bodyFontFamily,
                color = femaleTextColor,
                fontWeight = FontWeight.W400,
                letterSpacing = -(0.8).sp
            )
        }

    }
}

@Preview
@Composable
fun GenderSelectorComponentTest(){
    Box(
        modifier = Modifier
            .size(height = 100.dp, width = 400.dp)
            .background(color = textDim),
        contentAlignment = Alignment.Center
    ){
        var gender by remember { mutableStateOf<Gender>(Gender.FEMALE) }
        GenderSelectorComponent(
            value = gender,
            onClick = {gender = it}
        )
    }
}