package com.wildan.greensentry.model

import com.wildan.greensentry.api.ApiClient
import com.wildan.greensentry.api.UserApi
import retrofit2.Response

class UserRepository {

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return try {
            UserApi.getApi()?.loginUser(loginRequest = loginRequest)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun registerUser(registerRequest: RegisterRequest): Response<RequestResponse>? {
        return try {
            UserApi.getApi()?.registerUser(registerRequest = registerRequest)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun fetchNews(token: String): List<News> {
        return try {
            val response = UserApi.getApi()?.getNews(token)
            if (response != null && response.isSuccessful) {
                response.body()?.articles ?: emptyList()
            } else {
                throw Exception("Failed to fetch news: ${response?.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
