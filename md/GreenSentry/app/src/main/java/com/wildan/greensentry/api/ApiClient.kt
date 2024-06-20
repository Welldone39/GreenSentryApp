package com.wildan.greensentry.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val mHttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val mOkHttpClient = OkHttpClient.Builder()
        .addInterceptor(mHttpLoggingInterceptor)
        .build()

    private var mRetrofit: Retrofit? = null

     val client: Retrofit
        get() {
            if (mRetrofit == null) {
                mRetrofit = Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return mRetrofit!!
        }

    val userApi: UserApi by lazy {
        client.create(UserApi::class.java)
    }
}
