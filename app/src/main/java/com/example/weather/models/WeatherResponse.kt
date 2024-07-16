package com.example.weather.models

data class WeatherResponse(
    val main: Main
)

data class Main(
    val temp: Double,
    val feels_like: Double
)
