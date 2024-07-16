package com.example.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.models.WeatherResponse // Измените на правильное имя вашего файла модели
import com.example.weather.network.ApiClient // Измените на правильное имя вашего клиента API
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> get() = _weather

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchWeather(city: String, apiKey: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getWeather(city, apiKey)
                if (response.isSuccessful) {
                    _weather.value = response.body()
                }
            } catch (e: Exception) {

            } finally {
                _isLoading.value = false
            }
        }
    }
}




