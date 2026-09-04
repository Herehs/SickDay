package com.example.up.data.remote.mappers

import com.example.up.data.remote.dto.KpDataItem
import com.example.up.domain.model.KpData
import java.time.LocalDateTime

fun List<KpDataItem>.toDomain(): List<KpData> = map { data ->
    KpData(
        Kp = data.Kp,
        a_running = data.a_running,
        station_count = data.station_count,
        time = LocalDateTime.parse(data.time_tag).toLocalDate()
    )
}

