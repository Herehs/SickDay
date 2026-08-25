package com.example.up.domain.model

import java.util.Date

data class Note(
    val id: ULong,
    val date: Date,
    var generalHealth: Int,
    var note: String,
    var drowsiness: Int,
    var pressure: Int,
    var weakness: Int
)