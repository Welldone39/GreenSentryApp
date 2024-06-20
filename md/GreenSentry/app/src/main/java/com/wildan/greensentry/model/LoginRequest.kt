package com.wildan.greensentry.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)

data class RegisterRequest(
    @SerializedName("first_name")
    var first_name: String,
    @SerializedName("last_name")
    var last_name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)

data class ArticlesItem(
    @SerializedName("author")
    val author: String? = null,

    @SerializedName("source")
    val source: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("urlToImage")
    val urlToImage: String? = null
)