package com.wildan.greensentry.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    var status: String,
    @SerializedName("message")
    var message: String,
    @SerializedName("token")
    var token: String
)
data class RequestResponse(
    @SerializedName("status")
    var status: String,
    @SerializedName("message")
    var message: String
)
data class News(
    val author: String?,
    val source: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    @SerializedName("url_to_image")
    val url_To_Image: String?
)
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<News>
)
