package com.example.up.data.remote.mappers

import com.example.up.data.remote.dto.GraphDtoItem
import com.example.up.domain.model.GraphData

fun List<GraphDtoItem>.toDomain(): GraphData{
    return GraphData(list = map {it.risk.toFloat()})
}