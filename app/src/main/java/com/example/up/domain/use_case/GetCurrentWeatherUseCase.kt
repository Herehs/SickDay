package com.example.up.domain.use_case

import com.example.up.domain.model.CurrentWeather

class GetWeatherInfoUseCase {
    operator fun invoke(): CurrentWeather{
        return CurrentWeather(
            temperature = 12f,
            kp_index = 6f,
            pressure = 777,
            humidity = 81
        )
    }
}