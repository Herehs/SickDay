package com.example.up.presentation.screens.main_screen

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.up.presentation.common_сomponents.Section
import com.example.up.presentation.screens.main_screen.components.Advice
import com.example.up.presentation.screens.main_screen.components.AdviceList
import com.example.up.presentation.screens.main_screen.components.DateCardData
import com.example.up.presentation.screens.main_screen.components.DateCarousel
import com.example.up.presentation.screens.main_screen.components.IndexScale
import com.example.up.presentation.screens.main_screen.components.Tile
import com.example.up.presentation.ui.theme.bodyFontFamily
import com.example.up.presentation.ui.theme.text
import com.example.up.presentation.ui.theme.textDim
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = koinViewModel()
){

    val weatherInfo = mainViewModel.currentWeather.collectAsState()
    val adviceList = mainViewModel.adviseList.collectAsState()
    val currentDate = mainViewModel.selectedDate.collectAsState()
    val danger = mainViewModel.danger.collectAsState()
    val refresh = mainViewModel.refresh.collectAsState()

    PullToRefreshBox(
        isRefreshing = refresh.value,
        onRefresh = mainViewModel::onRefresh,
        modifier = modifier
    ) {
        when {
            weatherInfo.value.isLoading -> {
                LoadingScreen(modifier = Modifier.fillMaxSize())
            }
            weatherInfo.value.isError -> {
                ErrorScreen(modifier = Modifier.fillMaxSize())
            }
            else -> {
                MainScreenSuccess(
                    modifier = Modifier.fillMaxSize(),
                    adviceList = adviceList.value,
                    currentDate = currentDate.value,
                    weatherInfo = weatherInfo.value,
                    danger = danger.value
                ) { mainViewModel.selectDate(it.date) }
            }
        }
    }
}

@Composable
fun MainScreenSuccess(
    modifier: Modifier = Modifier,
    adviceList: List<Advice>,
    currentDate: LocalDate,
    weatherInfo: CurrentWeatherState,
    danger: Float,
    pickDate: (DateCardData) -> Unit
){
    val formatted = currentDate.format(DateTimeFormatter.ofPattern("LLLL yyyy")).replaceFirstChar { it.uppercase() }

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
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(top = 10.dp, bottom = 30.dp),
            text = formatted.format(DateTimeFormatter.ofPattern("LLLL yyyy")).replaceFirstChar { it.uppercase() },
            fontSize = 18.sp,
            lineHeight = 22.sp,
            fontFamily = bodyFontFamily,
            color = text,
            fontWeight = FontWeight.W400
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = " ${weatherInfo.temperature}°",
                fontSize = 64.sp,
                lineHeight = 64.sp,
                fontFamily = bodyFontFamily,
                color = text,
                fontWeight = FontWeight.W400
            )
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = "Средняя температура",
                fontSize = 18.sp,
                lineHeight = 18.sp,
                fontFamily = bodyFontFamily,
                color = text,
                fontWeight = FontWeight.W400
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Tile(
                    modifier = Modifier.weight(1f),
                    name = "Влажность",
                    value = "${weatherInfo.humidity.roundToInt()}%"
                )
                Tile(
                    modifier = Modifier.weight(1f),
                    name = "Индекс Кр",
                    value = "${weatherInfo.kp_index.roundToInt()}/9"
                )
                Tile(
                    modifier = Modifier.weight(1f),
                    name = "Давление",
                    value = weatherInfo.pressure.roundToInt().toString()
                )
            }
            IndexScale(
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        top = 20.dp,
                        end = 20.dp
                    ),
                text = "Индекс",
                value = danger,
            )
            Section(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xffD0EAFF).copy(alpha = 0.3f))
                    .padding(10.dp),
                name = "Советы на сегодня"
            ) {
                //уже захардкожено во вьюмодели
                // (ждём сервак =( )

                AdviceList(advices = adviceList)
            }

//            Text(
//                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(top = 30.dp),
//                text = "Принятие лекарств",
//                fontSize = 17.sp,
//                lineHeight = 22.sp,
//                fontFamily = bodyFontFamily,
//                color = text,
//                fontWeight = FontWeight.W400,
//                letterSpacing = -(0.8).sp
//            )
            //PillsSchedule(pillsList = pillsScheduleList.value, currentTime = LocalTime.of(14 ,0))


        }
    }

}




@Preview(showBackground = true)
@Composable
fun TestMainScreen(){
    MainScreenSuccess(
        adviceList = listOf(
            Advice(
                icon = R.drawable.heart_rate,
                text = "Снизьте физические нагрузки, избегайте резкого подъёма"
            ),
            Advice(
                icon = R.drawable.clock,
                text = "Пейте больше воды \n1.5–2 л в течение дня"
            ),
            Advice(
                icon = R.drawable.drop,
                text = "Ложитесь спать пораньше, ночью буря усилится"
            )
        )
        ,
        currentDate = LocalDate.now(),
        weatherInfo = CurrentWeatherState(),
        danger = 1f,
        pickDate = {  }
    )
}