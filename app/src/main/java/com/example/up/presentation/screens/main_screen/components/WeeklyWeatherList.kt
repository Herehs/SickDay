package com.example.up.presentation.screens.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.R
import com.example.up.domain.model.WeeklyWeather
import com.example.up.presentation.ui.theme.text
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.abs

@Composable
fun WeeklyWeatherList(
    weatherList: List<WeeklyWeather>,
    modifier: Modifier = Modifier
){
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(weatherList){ item ->
            WeeklyWeatherItem(
                modifier = Modifier.padding(horizontal = 5.dp),
                date = item.date,
                humidity = item.humidity,
                temperature = item.temperature
            )
        }
    }
}


@Composable
fun WeeklyWeatherItem(
    modifier: Modifier = Modifier,
    date: String = "2026-01-01",
    humidity: Double = .0,
    temperature: Double = .0
){
    val operator = if(temperature >= 0) " " else "-"
    val dateOfWeekFormatter = DateTimeFormatter.ofPattern("MM.dd")
    val dayOfWeekFormatter = DateTimeFormatter.ofPattern("E")
    val formattedDate = LocalDate.parse(date)
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xffD0EAFF).copy(alpha = 0.3f))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formattedDate.format(dateOfWeekFormatter) ?: "",
            fontSize = 14.sp,
            color = text
        )
        Text(
            text = formattedDate.format(dayOfWeekFormatter) ?: "",
            fontSize = 14.sp,
            color = text
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.temperature),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = text
            )
            Text(
                text = "${operator}${abs(temperature)}°",
                fontSize = 14.sp,
                color = text
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.droplet),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = text
            )
            Text(
                text = " ${humidity}%",
                fontSize = 14.sp,
                color = text
            )
        }

    }
}

@Preview
@Composable
fun Test(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        val a = mutableListOf<WeeklyWeather>()

        repeat(7){
            a.add(
                WeeklyWeather(
                    date = "2026-01-01"
                )
            )
        }
        WeeklyWeatherList(a)
    }
}