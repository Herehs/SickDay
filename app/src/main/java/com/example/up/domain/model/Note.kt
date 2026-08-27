package com.example.up.domain.model


data class Note(
    val id: Long = 0,
    val date: Long = 0,
    val generalHealth: Float = 0f,
    val note: String = "",
    val drowsiness: Float = 0f,
    val pressure: Float = 0f,
    val weakness: Float = 0f,
)