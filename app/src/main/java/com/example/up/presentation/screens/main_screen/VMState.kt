package com.example.up.presentation.screens.main_screen

data class PositionState(
    val lat: Float = 0f,
    val lon: Float = 0f,
    val isLoading: Boolean = true,
    val isError: Boolean = false
)