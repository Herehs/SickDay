package com.example.up.presentation.screens.calendar_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.common_сomponents.Background
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter

@Composable
fun CalendarComponent(
    modifier: Modifier = Modifier,
    date: LocalDate,
    pickedDate: (String) -> Unit
){
    var pickedDate by rememberSaveable { mutableStateOf(LocalDate.now()) }
    val daysList = generateDaysList(date)
    val days = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(7)
    ) {
        items(days){ day ->
            Text(
                modifier = Modifier,
                text = day,
                fontSize = 14.sp,
                lineHeight = 22.sp,
                fontFamily = bodyFontFamily,
                color = text,
                fontWeight = FontWeight.W500,
                maxLines = 1,
                textAlign = TextAlign.Center,
                letterSpacing = -0.8.sp
            )
        }
        items(daysList){ day ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(top = 10.dp)
            ){
                DateComponent(
                    modifier = Modifier.clickable(
                        onClick = {
                            pickedDate = day
                            pickedDate(day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        },
                        indication = null,
                        interactionSource = null,
                        enabled = day <= LocalDate.now()
                    ),
                    date = day,
                    pickedDate = pickedDate
                )
            }
        }
    }

}

@Composable
fun DateComponent(
    modifier: Modifier = Modifier,
    date: LocalDate,
    pickedDate: LocalDate
){
    var backgroundColor: Color
    var borderColor: Color


    if(date.month == LocalDate.now().month){
        backgroundColor = Color(0xffFFF5EA)
        borderColor = Color(0xffCAB597).copy(alpha = .3f)
    }
    else{
        backgroundColor = Color(0xffFFFFFF)
        borderColor = Color(0xffCAB597).copy(alpha = .3f)
    }
    if (date > LocalDate.now()){
            backgroundColor = Color(0xffFFFFFF)
            borderColor = Color(0xffCAB597).copy(alpha = .1f)
    }
    if (date == pickedDate){
        backgroundColor = Color(0xffFFAA56)
        borderColor = Color(0xffCAB597).copy(alpha = .3f)
    }

    Box(
        modifier = modifier
            .size(38.dp)
            .clip(shape = RoundedCornerShape(50))
            .background(color = backgroundColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(50.dp)
            ),
        contentAlignment = Alignment.Center
    ){
        Text(
            modifier = Modifier,
            text = date.dayOfMonth.toString(),
            fontSize = 14.sp,
            lineHeight = 22.sp,
            fontFamily = bodyFontFamily,
            color = text,
            fontWeight = FontWeight.W500,
            maxLines = 1,
            textAlign = TextAlign.Center,
            letterSpacing = (-0.8).sp
        )
    }
}

fun generateDaysList(startDate: LocalDate): List<LocalDate> {
    val firstDayOfMonth = startDate.withDayOfMonth(1)
    val lastDayOfMonth = startDate.withDayOfMonth(startDate.lengthOfMonth())

    val start = firstDayOfMonth.minusDays((firstDayOfMonth.dayOfWeek.value - 1).toLong())
    val end = lastDayOfMonth.plusDays((7 - lastDayOfMonth.dayOfWeek.value).toLong())

    val result = mutableListOf<LocalDate>()
    var current = start

    while (current <= end) {
        repeat(7) {
            result.add(current)
            current = current.plusDays(1)
        }
    }

    return result
}

@Preview
@Composable
fun CalendarComponentTest(){
    Scaffold(){ paddingValues ->
        Background()
        Column(modifier = Modifier.padding(paddingValues).padding(horizontal = 20.dp)) {
            CalendarComponent(date = LocalDate.of(2026, Month.MAY, 1), pickedDate = {})
        }
    }
}
