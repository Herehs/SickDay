package com.example.up.data.remote

object HttpRoutes {
    private const val BASE_URL = "http://10.175.22.201:8000"
    const val CURRENT_WEATHER = "$BASE_URL/current"
    const val CALENDAR = "$BASE_URL/calendar"
    const val AVG = "$BASE_URL/day/averages"
    const val GRAPH = "$BASE_URL/day/risk-graph"
}