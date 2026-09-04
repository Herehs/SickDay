package com.example.up.presentation.screens.calendar_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.up.common.Resource
import com.example.up.domain.use_case.GetAvgWeatherUseCase
import com.example.up.domain.use_case.GetCurrentPositionUseCase
import com.example.up.domain.use_case.GetCurrentWeatherUseCase
import com.example.up.domain.use_case.GetGraphDataUseCase
import com.example.up.presentation.screens.main_screen.CurrentWeatherState
import com.example.up.presentation.screens.main_screen.PositionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CalendarViewModel(
    private val getAvgWeatherUseCase: GetAvgWeatherUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getCurrentPositionUseCase: GetCurrentPositionUseCase,
    private val getGraphDataUseCase: GetGraphDataUseCase
): ViewModel() {

    private val _weather = MutableStateFlow(CurrentWeatherState())
    val  weather = _weather.asStateFlow()

    private val _position = MutableStateFlow(PositionState())
    val position = _position.asStateFlow()

    private val _pickedDate = MutableStateFlow("")
    val pickedDate = _pickedDate.asStateFlow()

    private val _graphData = MutableStateFlow(listOf(0f))
    val graphData = _graphData.asStateFlow()

    fun setDate(date: String){
        _pickedDate.value = date
        if(date != LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))){
            getGraphData()
            getWeatherInfo()
        }
        else{
            getCurrentWeather()
            _graphData.value = listOf(0f, 0f, 0f, 0f, 0f, 0f)
        }
    }


    private fun getCurrentWeather(){
        viewModelScope.launch {
            getCurrentWeatherUseCase(
                lat = _position.asStateFlow().value.lat,
                lon = _position.asStateFlow().value.lon
            ).collect {  result ->
                when(result){
                    is Resource.Success -> {
                        result.data?.let { weather ->
                            _weather.update {
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
                    }
                    is Resource.Loading -> {
                        _weather.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Resource.Error -> {
                        _weather.update {
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

    private fun getWeatherInfo(){
        viewModelScope.launch {
            getAvgWeatherUseCase(
                lat = _position.asStateFlow().value.lat,
                lon = _position.asStateFlow().value.lon,
                date = _pickedDate.asStateFlow().value
            ).collect {  result ->
                when(result){
                    is Resource.Success -> {
                        result.data?.let { weather ->
                            _weather.update {
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
                    }
                    is Resource.Loading -> {
                        _weather.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Resource.Error -> {
                        _weather.update {
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

    fun getGraphData(){
        viewModelScope.launch {
            getGraphDataUseCase(
                lat = _position.asStateFlow().value.lat,
                lon = _position.asStateFlow().value.lon,
                date = _pickedDate.asStateFlow().value
            ).collect {  result ->
                result.data?.let { graphData ->
                    _graphData.value = graphData.list
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

    init {
        setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))

        getPosition()
    }
}