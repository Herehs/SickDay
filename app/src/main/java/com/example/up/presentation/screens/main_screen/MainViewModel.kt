package com.example.up.presentation.screens.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.up.R
import com.example.up.common.Resource
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

    private val _currentWeather = MutableStateFlow(CurrentWeatherState())
    val currentWeather = _currentWeather.asStateFlow()

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate = _selectedDate.asStateFlow()

    private val _position = MutableStateFlow(PositionState())
    val position = _position.asStateFlow()

    private val _danger = MutableStateFlow(0f)
    val danger = _danger.asStateFlow()

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }

    private fun getWeatherInfo(){
        viewModelScope.launch {
            getCurrentWeatherUseCase(
                lat = _position.asStateFlow().value.lat,
                lon = _position.asStateFlow().value.lon
            ).collect {  result ->
                when(result){
                    is Resource.Success -> {
                        result.data?.let { weather ->
                            _currentWeather.update {
                                it.copy(
                                    humidity = weather.humidity,
                                    kp_index = weather.kp_index,
                                    pressure = weather.pressure,
                                    temperature = weather.temperature,
                                    isLoading = false,
                                    isError = false
                                )
                            }
                        }
                        calculateDangerCoefficient()
                    }
                    is Resource.Loading -> {
                        _currentWeather.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Resource.Error -> {
                        _currentWeather.update {
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

    fun getPosition(){
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

                            getWeatherInfo()
                        }
                    }
                    is Resource.Loading -> {
                        _position.update {
                            it.copy(
                                isLoading = true,
                            )
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

    private fun calculateDangerCoefficient() {
        if (_currentWeather.value.isLoading || _currentWeather.value.isError) {
            _danger.value = 0f
        }

        val temperatureCoefficient = when {
            _currentWeather.value.temperature < -30 -> 1f
            _currentWeather.value.temperature > 30 -> 1f
            _currentWeather.value.temperature in -10.0..20.0 -> 0f
            else -> {
                when {
                    _currentWeather.value.temperature < -10 -> {
                        (-_currentWeather.value.temperature - 10) / 20f
                    }
                    else -> {
                        (_currentWeather.value.temperature - 20) / 20f
                    }
                }
            }
        }

        val humidityCoefficient = when {
            _currentWeather.value.humidity in 40f..60f -> 0f
            _currentWeather.value.humidity < 40f -> (40f - _currentWeather.value.humidity) / 40f
            else -> (_currentWeather.value.humidity - 60f) / 40f
        }.coerceIn(0f, 1f)

        val kpCoefficient = when {
            _currentWeather.value.kp_index <= 3 -> 0f
            else -> ((_currentWeather.value.kp_index - 3) / 6f).coerceIn(0f, 1f)
        }

        val normalPressure = 760f
        val pressureDeviation = kotlin.math.abs(_currentWeather.value.pressure - normalPressure)
        val pressureCoefficient = (pressureDeviation / 30f).coerceIn(0f, 1f)


        val weights = mapOf(
            "temperature" to 0.35f,
            "humidity" to 0.15f,
            "kp" to 0.30f,
            "pressure" to 0.20f
        )

        val dangerCoefficient =
            temperatureCoefficient * weights["temperature"]!! +
                    humidityCoefficient * weights["humidity"]!! +
                    kpCoefficient * weights["kp"]!! +
                    pressureCoefficient * weights["pressure"]!!

        _danger.value = (dangerCoefficient.coerceIn(0f, 1f) * 100).toInt() / 100f
    }

    init {
        getPosition()


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
    }
}