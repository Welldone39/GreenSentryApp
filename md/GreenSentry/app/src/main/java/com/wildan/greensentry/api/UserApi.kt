package com.wildan.greensentry.api

import com.wildan.greensentry.model.LoginRequest
import com.wildan.greensentry.model.LoginResponse
import com.wildan.greensentry.model.NewsResponse
import com.wildan.greensentry.model.RegisterRequest
import com.wildan.greensentry.model.RequestResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {

    @POST("api/v1/users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("api/v1/users/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RequestResponse>

    @GET("api/v1/articles")
    suspend fun getNews(@Header("Authorization") token: String): Response<NewsResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}
