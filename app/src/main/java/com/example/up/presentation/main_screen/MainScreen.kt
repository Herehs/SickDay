package com.example.up.presentation.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.common_сomponents.Section
import com.example.up.presentation.main_screen.components.AdviceList
import com.example.up.presentation.main_screen.components.Background
import com.example.up.presentation.main_screen.components.DateCarousel
import com.example.up.presentation.main_screen.components.IndexScale
import com.example.up.presentation.main_screen.components.PillsSchedule
import com.example.up.presentation.main_screen.components.Tile
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun MainScreen(
    mainViewModel: MainViewmodel = koinViewModel()
){
    val adviceList = mainViewModel.adviseList.collectAsState()
    val pillsScheduleList = mainViewModel.pillsList.collectAsState()
    val pressure = mainViewModel.atmPressure.collectAsState()
    val KRIndex = mainViewModel.KRIndex.collectAsState()
    val temperature = mainViewModel.temperature.collectAsState()
    val humidity = mainViewModel.humidity.collectAsState()

    var currentDate by remember { mutableStateOf(LocalDate.now())}
    val formatted = currentDate.format(DateTimeFormatter.ofPattern("LLLL yyyy")).replaceFirstChar { it.uppercase() }

    Scaffold() { innerPadding ->
        Background()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = formatted,
                fontSize = 18.sp,
                lineHeight = 22.sp,
                fontFamily = bodyFontFamily,
                color = text,
                fontWeight = FontWeight.W400
            )
            DateCarousel(modifier = Modifier.padding(top = 10.dp).height(100.dp), pickedDay = {currentDate = it.date})
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IndexScale(text = "Индекс", value = .68f, modifier = Modifier.padding(horizontal = 20.dp))

                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .padding(horizontal = 20.dp),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Tile(
                            name = "Давление",
                            tileContent = {

                                Text(
                                    modifier = Modifier.padding(bottom = 4.dp),
                                    text = buildAnnotatedString {
                                        withStyle(style = SpanStyle(
                                            fontSize = 32.sp,
                                            fontFamily = bodyFontFamily,
                                            color = text,
                                            fontWeight = FontWeight.W400
                                        )
                                        ){
                                            append(pressure.value.toString())
                                        }
                                        withStyle(style = SpanStyle(
                                            fontSize = 16.sp,
                                            fontFamily = bodyFontFamily,
                                            color = text,
                                            fontWeight = FontWeight.W400,
                                            letterSpacing = -(1).sp
                                        )
                                        ){
                                            append(" мм рт ст")
                                        }
                                    },
                                    fontSize = 18.sp,
                                    lineHeight = 22.sp,
                                    fontFamily = bodyFontFamily,
                                    color = text,
                                    fontWeight = FontWeight.W400
                                )
                            }
                        )

                    }
                    item {
                        Tile(
                            name = "Индекс Кр",
                            tileContent = {

                                Text(
                                    modifier = Modifier.padding(bottom = 4.dp),
                                    text = buildAnnotatedString {
                                        withStyle(style = SpanStyle(
                                            fontSize = 32.sp,
                                            fontFamily = bodyFontFamily,
                                            color = text,
                                            fontWeight = FontWeight.W400
                                        )
                                        ){
                                            append("${KRIndex.value}/")
                                        }
                                        withStyle(style = SpanStyle(
                                            fontSize = 24.sp,
                                            fontFamily = bodyFontFamily,
                                            color = text,
                                            fontWeight = FontWeight.W400
                                        )
                                        ){
                                            append("9")
                                        }
                                    },
                                    fontSize = 18.sp,
                                    lineHeight = 22.sp,
                                    fontFamily = bodyFontFamily,
                                    color = text,
                                    fontWeight = FontWeight.W400
                                )
                            }
                        )

                    }
                    item {
                        Tile(
                            name = "Температура",
                            tileContent = {
                                Text(
                                    modifier = Modifier.padding(bottom = 4.dp),
                                    text = buildAnnotatedString {
                                        withStyle(style = SpanStyle(
                                            fontSize = 32.sp,
                                            fontFamily = bodyFontFamily,
                                            color = text,
                                            fontWeight = FontWeight.W400
                                        )
                                        ){
                                            append("${temperature.value}")
                                        }
                                    },
                                    fontSize = 18.sp,
                                    lineHeight = 22.sp,
                                    fontFamily = bodyFontFamily,
                                    color = text,
                                    fontWeight = FontWeight.W400
                                )
                            }
                        )
                    }
                    item {
                        Tile(
                            name = "Влажность",
                            tileContent = {
                                Text(
                                    modifier = Modifier.padding(bottom = 4.dp),
                                    text = buildAnnotatedString {
                                        withStyle(style = SpanStyle(
                                            fontSize = 32.sp,
                                            fontFamily = bodyFontFamily,
                                            color = text,
                                            fontWeight = FontWeight.W400
                                        )
                                        ){
                                            append("${humidity.value}%")
                                        }
                                    },
                                    fontSize = 18.sp,
                                    lineHeight = 22.sp,
                                    fontFamily = bodyFontFamily,
                                    color = text,
                                    fontWeight = FontWeight.W400
                                )
                            }
                        )

                    }
                }
                Section(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 19.dp)
                        .padding(horizontal = 20.dp),
                    name = "Советы на сегодня"
                ) {
                    //уже захардкожено во вьюмодели
                    // (ждём сервак =( )

                    AdviceList(advices = adviceList.value)
                }

                Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    text = "Принятие лекарств",
                    fontSize = 17.sp,
                    lineHeight = 22.sp,
                    fontFamily = bodyFontFamily,
                    color = text,
                    fontWeight = FontWeight.W400,
                    letterSpacing = -(0.8).sp
                )
                PillsSchedule(pillsList = pillsScheduleList.value, currentTime = LocalTime.of(14 ,0))


            }
        }
    }
}

@Preview
@Composable
fun TestMainScreen(){
    MainScreen()
}