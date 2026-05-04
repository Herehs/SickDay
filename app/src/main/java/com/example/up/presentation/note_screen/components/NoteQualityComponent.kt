package com.example.up.presentation.note_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.R
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.openedQualityComponent
import com.example.up.presentation.ui.theme.texDark
import com.example.up.presentation.ui.theme.text

@Composable
fun NoteQualityComponent(
    modifier: Modifier = Modifier,
    name: String
){
    val opened by rememberSaveable { mutableStateOf(false) }
    var sliderPosition by remember { mutableFloatStateOf(0f) }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = openedQualityComponent.copy(alpha = .4f))
            .border(
                width = 1.dp,
                color = Color(0xff9F8A8F).copy(alpha = .2f),
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.open_eye),
                    contentDescription = null,
                    modifier = Modifier.height(14.dp)
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = name,
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
                modifier = Modifier.height(14.dp)
            )
        }
//        val sliderState: SliderState = SliderState()
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                thumbColor = Color(0xff531111),
                activeTrackColor = Color(0xff531111),
                inactiveTrackColor = Color(0xff9C9C9C),
            ),
            steps = 3,
            valueRange = 0f..10f

        )


    }
}


@Preview
@Composable
fun NoteQualityComponentTest(){
    NoteQualityComponent(
        name = "Давление"
    )
}
