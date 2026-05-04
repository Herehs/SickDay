package com.example.up.presentation.calendar_screen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.calendar_screen.components.CalendarComponent
import com.example.up.presentation.calendar_screen.components.ChartComponent
import com.example.up.presentation.main_screen.components.Background
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
){
    val pressure = 677
    val KRIndex = 6
    val temperature = 6.7f
    val humidity = 67f
    val currDate = LocalDate.now()
    Box(){
        Background()
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    modifier = Modifier,
                    text = currDate.format(DateTimeFormatter.ofPattern("LLLL yyyy")).replaceFirstChar { it.uppercase() },
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    fontFamily = bodyFontFamily,
                    color = text,
                    fontWeight = FontWeight.W400
                )
            }
            CalendarComponent(
                date = currDate,
                modifier = Modifier.padding(bottom = 36.dp).padding(horizontal = 20.dp)
            )

            ChartComponent(
                modifier = Modifier.padding(horizontal = 20.dp),
                date = LocalDate.now(),
                lastUpdate = 67,
                healthScore = 67,
                data = listOf(0f, 35f, 70f, 70f, 100f)
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
                    Text(
                        modifier = Modifier,
                        text = "${humidity.toInt()}%",
                        fontSize = 20.sp,
                        lineHeight = 22.sp,
                        fontFamily = bodyFontFamily,
                        color = Color(0xffFF5D5D),
                        fontWeight = FontWeight.W400,
                        letterSpacing = -(0.2).sp
                    )
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
                    Text(
                        modifier = Modifier,
                        text = "+${temperature}",
                        fontSize = 20.sp,
                        lineHeight = 22.sp,
                        fontFamily = bodyFontFamily,
                        color = Color(0xff21DB8E),
                        fontWeight = FontWeight.W400,
                        letterSpacing = -(0.2).sp
                    )
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
                                append("${pressure}")
                            }
                            withStyle(style = SpanStyle(
                                fontSize = 12.sp,
                                fontFamily = bodyFontFamily,
                                color = Color(0xffFFD666),
                                fontWeight = FontWeight.W400,
                                letterSpacing = -(0.2).sp
                            )
                            ){
                                append("мм рт ст")
                            }
                        },
                        fontSize = 20.sp,
                        lineHeight = 22.sp,
                        fontFamily = bodyFontFamily,
                        fontWeight = FontWeight.W400,
                        letterSpacing = -(0.2).sp
                    )
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
                                append("${KRIndex}/")
                            }
                            withStyle(style = SpanStyle(
                                fontSize = 15.sp,
                                fontFamily = bodyFontFamily,
                                color = text,
                                fontWeight = FontWeight.W400,
                                letterSpacing = -(0.2).sp
                            )
                            ){
                                append("7")
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


@Preview
@Composable
fun CalendarTest(){
    Column {
        CalendarScreen()
    }
}