package com.example.up.domain.use_case

import com.example.up.domain.model.WeatherInfoState

class GetWeatherInfoUseCase {
    operator fun invoke(): WeatherInfoState{
        return WeatherInfoState(
            temperature = 12,
            kp_index = 6f,
            pressure = 777f,
            humidity = 81f
        )
    }
}