package com.example.up.presentation.screens.main_screen

data class PositionState(
    val lat: Float = 0f,
    val lon: Float = 0f,
    val isLoading: Boolean = true,
    val isError: Boolean = false
)

data class CurrentWeatherState(
    val humidity: Float = 0f,
    val kp_index: Float = 0f,
    val pressure: Float = 0f,
    val temperature: Float = 0f,
    val isLoading: Boolean = true,
    val isError: Boolean = false
)