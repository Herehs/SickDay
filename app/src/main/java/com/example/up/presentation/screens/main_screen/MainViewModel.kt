package com.example.up.presentation.screens.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.up.R
import com.example.up.common.Resource
import com.example.up.domain.model.CurrentWeather
import com.example.up.domain.model.Position
import com.example.up.domain.use_case.GetCurrentPositionUseCase
import com.example.up.domain.use_case.GetCurrentWeatherUseCase
import com.example.up.presentation.screens.main_screen.components.Advice
import com.example.up.presentation.screens.main_screen.components.PillsScheduleData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class MainViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getCurrentPositionUseCase: GetCurrentPositionUseCase
) : ViewModel() {

    private val _adviseList = MutableStateFlow(emptyList<Advice>())
    val adviseList = _adviseList.asStateFlow()

    private val _pillsList = MutableStateFlow(emptyList<PillsScheduleData>())
    val pillsList = _pillsList.asStateFlow()

    private val _currentWeather = MutableStateFlow(CurrentWeather(
        temperature = 0f,
        kp_index = 0f,
        pressure = 0,
        humidity = 0
    ))
    val currentWeather = _currentWeather.asStateFlow()

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate = _selectedDate.asStateFlow()

    private val _position = MutableStateFlow(PositionState())
    val position = _position.asStateFlow()

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }

    private fun getWeatherInfo(){
        viewModelScope.launch {
            _currentWeather.value = getCurrentWeatherUseCase(lat = _position.asStateFlow().value.lat, lon = _position.asStateFlow().value.lon)
        }
    }

    private fun getPosition(){
        viewModelScope.launch {
            getCurrentPositionUseCase().collect { result ->
                when(result){
                    is Resource.Success -> {
                        result.data?.let { position ->
                            _position.update {
                                it.copy(
                                    lat = position.lat,
                                    lon = position.lon,
                                    isLoading = false,
                                    isError = false
                                )
                            }
                        }
                    }
                    is Resource.Loading -> {
                        result.data?.let {
                            _position.update {
                                it.copy(
                                    isLoading = true,
                                )
                            }
                        }

                    }
                    is Resource.Error -> {
                        _position.update {
                            it.copy(
                                isLoading = false,
                                isError = true
                            )
                        }
                    }
                }
            }
        }
    }

    init {
        getPosition()
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