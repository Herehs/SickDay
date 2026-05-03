package com.example.up.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text
import com.example.up.presentation.ui.theme.textDark
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun PillsSchedule(
    modifier: Modifier = Modifier,
    pillsList: List<PillsScheduleData>,
    currentTime: LocalTime
){
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        itemsIndexed(pillsList){ index, item ->
            val shape = RoundedCornerShape(
                topStart = if(index == 0) 6.dp else 0.dp,
                bottomStart = if(index == 0) 6.dp else 0.dp,
                topEnd = if (index == pillsList.lastIndex) 6.dp else 0.dp,
                bottomEnd = if (index == pillsList.lastIndex) 6.dp else 0.dp,
            )

            Column(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .width(110.dp)
                    .height(58.dp)
                    .clip(shape = shape )
                    .background(
                        Color(
                            when(item.taken){
                                true -> 0xffE9FFDE
                                false -> {
                                    if(item.time < currentTime){
                                        0xffFEE5E5
                                    }
                                    else{
                                        0xffffffff
                                    }
                                }
                            }
                        )
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xffCCA7A7).copy(alpha = .54f),
                        shape = shape
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = item.name,
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    fontFamily = bodyFontFamily,
                    color = text,
                    fontWeight = FontWeight.W500,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    letterSpacing = -0.8.sp
                )
                Text(
                    modifier = Modifier,
                    text = item.time.format(DateTimeFormatter.ofPattern("H:mm")),
                    fontSize = 13.sp,
                    lineHeight = 14.sp,
                    fontFamily = bodyFontFamily,
                    color = textDark,
                    fontWeight = FontWeight.W500,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    letterSpacing = -0.8.sp
                )
            }
        }
    }
}

data class PillsScheduleData(
    val name: String,
    val date: LocalDate,
    val time: LocalTime,
    val taken: Boolean = false
)

@Preview
@Composable
fun PillsScheduleTest(){
    val list = listOf(
        PillsScheduleData(
            name = "Фенибут",
            date = LocalDate.now(),
            time = LocalTime.of(12, 0)
        ),
        PillsScheduleData(
            name = "Лирика",
            date = LocalDate.now(),
            time = LocalTime.of(16, 0),
            taken = true
        ),
        PillsScheduleData(
            name = "Фенозепам",
            date = LocalDate.now(),
            time = LocalTime.of(18, 0)
        )
    )
    PillsSchedule(pillsList = list, currentTime = LocalTime.of(14 ,0))
}