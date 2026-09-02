package com.example.up.presentation.screens.calendar_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.R
import com.example.up.presentation.common_сomponents.ErrorScreen
import com.example.up.presentation.common_сomponents.LoadingScreen
import com.example.up.presentation.screens.calendar_screen.components.CalendarComponent
import com.example.up.presentation.screens.calendar_screen.components.ChartComponent
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
){
    val vm: CalendarViewModel = koinViewModel()
    val weather = vm.weather.collectAsState()

    var dateDifference: Long by remember { mutableStateOf(0) }

    fun updateDateDifference(value: Long) {
        dateDifference = value.coerceIn(-3L, 1L)
    }
    val selectedMonth = LocalDate.now().plusMonths(dateDifference)
    val pickedDay = vm.pickedDate.collectAsState()
    val graphData = vm.graphData.collectAsState()


    Box(){
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 30.dp)
                    .padding(horizontal = 110.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ){
                IconButton(
                    onClick = { updateDateDifference(dateDifference - 1) },
                    interactionSource = null,
                    modifier = Modifier.size(22.dp),
                    colors = IconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = text,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color(0xff141414).copy(alpha = .5f)
                    ),
                    enabled = dateDifference != -2L
                ) {
                    Icon(
                        painter = painterResource(R.drawable.left_arrow),
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = selectedMonth.format(DateTimeFormatter.ofPattern("LLLL yyyy")).replaceFirstChar { it.uppercase() },
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    fontFamily = bodyFontFamily,
                    color = text,
                    fontWeight = FontWeight.W400
                )
                IconButton(
                    onClick = { updateDateDifference(dateDifference + 1) },
                    interactionSource = null,
                    modifier = Modifier.size(22.dp),
                    colors = IconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = text,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color(0xff141414).copy(alpha = .5f)
                    ),
                    enabled = dateDifference != 1L
                ) {
                    Icon(
                        painter = painterResource(R.drawable.right_arrow),
                        contentDescription = null
                    )
                }
            }
            CalendarComponent(
                Modifier
                    .padding(bottom = 36.dp)
                    .padding(horizontal = 20.dp),
                date = selectedMonth,
                pickedDate = {
                    vm.setDate(it)
                }
            )
            when {
                weather.value.isLoading -> {
                    LoadingScreen(modifier = modifier)
                }
                weather.value.isError -> {
                    ErrorScreen(modifier = modifier)
                }
                else -> {
                    ChartComponent(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        date = LocalDate.parse( pickedDay.value, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        data = graphData.value
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(color = Color.White.copy(alpha = .57f))
                            .border(
                                width = 1.dp,
                                color = Color(0xffCCA7A7).copy(alpha = .25f)
                            )
                            .padding(horizontal = 25.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Влажность",
                                fontSize = 10.sp,
                                lineHeight = 14.sp,
                                fontFamily = bodyFontFamily,
                                color = text.copy(alpha = .7f),
                                fontWeight = FontWeight.W400,
                                letterSpacing = -(0.2).sp
                            )
                            Box(
                                modifier = Modifier.height(20.dp)
                            ){
                                when {
                                    weather.value.isLoading -> {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(20.dp),
                                            color = text.copy(alpha = .7f),
                                            strokeWidth = 2.dp
                                        )
                                    }
                                    weather.value.isError -> {
                                        Text(
                                            modifier = Modifier,
                                            text = "Ошибка",
                                            fontSize = 20.sp,
                                            lineHeight = 22.sp,
                                            fontFamily = bodyFontFamily,
                                            color = Color.Red,
                                            fontWeight = FontWeight.W400,
                                            letterSpacing = -(0.2).sp
                                        )
                                    }
                                    else -> {
                                        Text(
                                            modifier = Modifier,
                                            text = "${weather.value.humidity.toInt()}%",
                                            fontSize = 20.sp,
                                            lineHeight = 22.sp,
                                            fontFamily = bodyFontFamily,
                                            color = Color(0xffFF5D5D),
                                            fontWeight = FontWeight.W400,
                                            letterSpacing = -(0.2).sp
                                        )
                                    }
                                }
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Температура",
                                fontSize = 10.sp,
                                lineHeight = 14.sp,
                                fontFamily = bodyFontFamily,
                                color = text.copy(alpha = .7f),
                                fontWeight = FontWeight.W400,
                                letterSpacing = -(0.2).sp
                            )
                            Box(
                                modifier = Modifier.height(20.dp)
                            ){
                                when {
                                    weather.value.isLoading -> {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(20.dp),
                                            color = text.copy(alpha = .7f),
                                            strokeWidth = 2.dp
                                        )
                                    }
                                    weather.value.isError -> {
                                        Text(
                                            modifier = Modifier,
                                            text = "Ошибка",
                                            fontSize = 20.sp,
                                            lineHeight = 22.sp,
                                            fontFamily = bodyFontFamily,
                                            color = Color.Red,
                                            fontWeight = FontWeight.W400,
                                            letterSpacing = -(0.2).sp
                                        )
                                    }
                                    else -> {
                                        Text(
                                            modifier = Modifier,
                                            text = "${(weather.value.temperature * 10f).toInt() / 10f}",
                                            fontSize = 20.sp,
                                            lineHeight = 22.sp,
                                            fontFamily = bodyFontFamily,
                                            color = Color(0xff21DB8E),
                                            fontWeight = FontWeight.W400,
                                            letterSpacing = -(0.2).sp
                                        )
                                    }
                                }
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Давление",
                                fontSize = 10.sp,
                                lineHeight = 14.sp,
                                fontFamily = bodyFontFamily,
                                color = text.copy(alpha = .7f),
                                fontWeight = FontWeight.W400,
                                letterSpacing = -(0.2).sp
                            )
                            Box(
                                modifier = Modifier.height(20.dp)
                            ){
                                when {
                                    weather.value.isLoading -> {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(20.dp),
                                            color = text.copy(alpha = .7f),
                                            strokeWidth = 2.dp
                                        )
                                    }
                                    weather.value.isError -> {
                                        Text(
                                            modifier = Modifier,
                                            text = "Ошибка",
                                            fontSize = 20.sp,
                                            lineHeight = 22.sp,
                                            fontFamily = bodyFontFamily,
                                            color = Color.Red,
                                            fontWeight = FontWeight.W400,
                                            letterSpacing = -(0.2).sp
                                        )
                                    }
                                    else -> {
                                        Text(
                                            modifier = Modifier,
                                            text = buildAnnotatedString {
                                                withStyle(style = SpanStyle(
                                                    fontSize = 20.sp,
                                                    fontFamily = bodyFontFamily,
                                                    color = Color(0xffFFD666),
                                                    fontWeight = FontWeight.W400,
                                                    letterSpacing = -(0.2).sp
                                                )
                                                ){
                                                    append("${weather.value.pressure.roundToInt()}")
                                                }
                                            },
                                            fontSize = 20.sp,
                                            lineHeight = 22.sp,
                                            fontFamily = bodyFontFamily,
                                            fontWeight = FontWeight.W400,
                                            letterSpacing = -(0.2).sp
                                        )
                                    }
                                }
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Индекс КР",
                                fontSize = 10.sp,
                                lineHeight = 14.sp,
                                fontFamily = bodyFontFamily,
                                color = text.copy(alpha = .7f),
                                fontWeight = FontWeight.W400,
                                letterSpacing = -(0.2).sp
                            )
                            Box(
                                modifier = Modifier.height(20.dp)
                            ){
                                when {
                                    weather.value.isLoading -> {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(20.dp),
                                            color = text.copy(alpha = .7f),
                                            strokeWidth = 2.dp
                                        )

                                    }
                                    weather.value.isError -> {
                                        Text(
                                            modifier = Modifier,
                                            text = "Ошибка",
                                            fontSize = 20.sp,
                                            lineHeight = 22.sp,
                                            fontFamily = bodyFontFamily,
                                            color = Color.Red,
                                            fontWeight = FontWeight.W400,
                                            letterSpacing = -(0.2).sp
                                        )
                                    }
                                    else -> {
                                        Text(
                                            modifier = Modifier,
                                            text = buildAnnotatedString {
                                                withStyle(style = SpanStyle(
                                                    fontSize = 20.sp,
                                                    fontFamily = bodyFontFamily,
                                                    color = text,
                                                    fontWeight = FontWeight.W400,
                                                    letterSpacing = -(0.2).sp
                                                )
                                                ){
                                                    append("${weather.value.kp_index.roundToInt()}/")
                                                }
                                                withStyle(style = SpanStyle(
                                                    fontSize = 15.sp,
                                                    fontFamily = bodyFontFamily,
                                                    color = text,
                                                    fontWeight = FontWeight.W400,
                                                    letterSpacing = -(0.2).sp
                                                )
                                                ){
                                                    append("9")
                                                }
                                            },
                                            fontSize = 20.sp,
                                            lineHeight = 22.sp,
                                            fontFamily = bodyFontFamily,
                                            fontWeight = FontWeight.W400,
                                            letterSpacing = -(0.2).sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun CalendarTest(){
    Column {
        CalendarScreen()
    }
}