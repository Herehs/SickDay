package com.example.up.presentation.screens.main_screen

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.up.presentation.common_сomponents.Section
import com.example.up.presentation.screens.main_screen.components.AdviceList
import com.example.up.presentation.screens.main_screen.components.DateCarousel
import com.example.up.presentation.screens.main_screen.components.IndexScale
import com.example.up.presentation.screens.main_screen.components.PillsSchedule
import com.example.up.presentation.screens.main_screen.components.Tile
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text
import org.koin.androidx.compose.koinViewModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = koinViewModel()
){
    val adviceList = mainViewModel.adviseList.collectAsState()
    val pillsScheduleList = mainViewModel.pillsList.collectAsState()
    val currentDate = mainViewModel.selectedDate.collectAsState()
    val weatherInfo = mainViewModel.currentWeather.collectAsState()
    val position = mainViewModel.position.collectAsState()

    val formatted = currentDate.value.format(DateTimeFormatter.ofPattern("LLLL yyyy")).replaceFirstChar { it.uppercase() }

    val locationLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
    }

    LaunchedEffect(Unit) {
        locationLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp).padding(top = 10.dp),
            text = formatted.format(DateTimeFormatter.ofPattern("LLLL yyyy")).replaceFirstChar { it.uppercase() },
            fontSize = 18.sp,
            lineHeight = 22.sp,
            fontFamily = bodyFontFamily,
            color = text,
            fontWeight = FontWeight.W400
        )

        DateCarousel(modifier = Modifier.padding(top = 10.dp).height(100.dp), pickedDay = {mainViewModel.selectDate(it.date)})
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IndexScale(text = "Индекс", value = .68f, modifier = Modifier.padding(horizontal = 20.dp))
            Text(
                text = if (position.value.isLoading){
                    "Loading..."
                } else if(position.value.isError){
                    "Error"
                }
                else{
                    "lat:${position.value.lat} \nlon:${position.value.lon}"
                },
                color = text
            )
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
                                        append(weatherInfo.value.pressure.toString())
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
                                        append("${weatherInfo.value.kp_index.roundToInt()}/")
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
                                        append("${weatherInfo.value.temperature}")
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
                                        append("${weatherInfo.value.humidity}%")
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
                    .padding(top = 40.dp)
                    .padding(horizontal = 20.dp),
                name = "Советы на сегодня"
            ) {
                //уже захардкожено во вьюмодели
                // (ждём сервак =( )

                AdviceList(advices = adviceList.value)
            }

            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(top = 30.dp),
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

@Preview
@Composable
fun TestMainScreen(){
    MainScreen()
}