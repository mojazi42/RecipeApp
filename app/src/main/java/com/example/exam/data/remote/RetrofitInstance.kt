package com.example.exam.data.remote

import com.example.exam.data.remote.api.RecipeFoodApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance{
    private val BASE_URL = "https://food2fork.ca/"
    private val API_KEY = "9c8b06d329136da358c2d00e76946b0111ce2c48"

    private val client = OkHttpClient.Builder()
        .addInterceptor{ chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Token $API_KEY")
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api: RecipeFoodApi by lazy {
        retrofit.create(RecipeFoodApi::class.java)
    }
}