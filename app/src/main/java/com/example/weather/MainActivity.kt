package com.example.weather

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.weather.viewmodel.WeatherViewModel
import com.example.weather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()
    private val apiKey = "77926dd5bb67716ff511f97283213ab0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Weather App"

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchWeather("Saint Petersburg", apiKey)
        }

        viewModel.weather.observe(this, Observer { weatherResponse ->
            binding.tvTemperature.text = "Температура: ${weatherResponse.main.temp}°C"
            binding.tvFeelsLike.text = "Ощущается как: ${weatherResponse.main.feels_like}°C"
            binding.swipeRefreshLayout.isRefreshing = false
        })

        // Наблюдаем за состоянием загрузки
        viewModel.isLoading.observe(this, Observer { isLoading ->
            binding.swipeRefreshLayout.isRefreshing = isLoading
        })

        binding.swipeRefreshLayout.isRefreshing = true
        viewModel.fetchWeather("Saint Petersburg", apiKey)
    }
}




