package com.example.up.presentation.screens.main_screen

import androidx.lifecycle.ViewModel
import com.example.up.R
import com.example.up.domain.model.WeatherInfoState
import com.example.up.domain.use_case.GetWeatherInfoUseCase
import com.example.up.presentation.screens.main_screen.components.Advice
import com.example.up.presentation.screens.main_screen.components.PillsScheduleData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.LocalTime

class MainViewModel(
    private val getWeatherInfoUseCase: GetWeatherInfoUseCase
) : ViewModel() {

    private val _adviseList = MutableStateFlow(emptyList<Advice>())
    val adviseList = _adviseList.asStateFlow()

    private val _pillsList = MutableStateFlow(emptyList<PillsScheduleData>())
    val pillsList = _pillsList.asStateFlow()

    private val _weatherInfoState = MutableStateFlow(WeatherInfoState())
    val weatherInfoState = _weatherInfoState.asStateFlow()

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate = _selectedDate.asStateFlow()

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }

    private fun getWeatherInfo(){
        _weatherInfoState.value = getWeatherInfoUseCase()
    }

    init {
        getWeatherInfo()

        _adviseList.value = listOf(
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

        _pillsList.value = listOf(
            PillsScheduleData(
                name = "Фенозепам",
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
                name = "Фенибут",
                date = LocalDate.now(),
                time = LocalTime.of(18, 0)
            ),
            PillsScheduleData(
                name = "Фенозепам",
                date = LocalDate.now(),
                time = LocalTime.of(21, 0)
            ),
        )
    }
}