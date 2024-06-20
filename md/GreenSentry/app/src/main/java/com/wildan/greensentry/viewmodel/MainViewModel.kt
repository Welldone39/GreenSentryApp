package com.wildan.greensentry.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wildan.greensentry.model.UserRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    private val userRepository = UserRepository()

    fun fetchNews(token: String) = liveData(Dispatchers.IO) {
        try {
            val newsList = userRepository.fetchNews(token)
            emit(newsList)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(emptyList())
        }
    }
}