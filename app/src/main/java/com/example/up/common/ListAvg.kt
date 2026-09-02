package com.example.up.common

import com.example.up.domain.model.Weather

fun List<Float>.avg(): Float = map { it }.average().toFloat()