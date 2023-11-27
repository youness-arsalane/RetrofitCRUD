package com.example.retrofitcrud.data.retrofit

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitBuilder {
//    private const val BASE_URL = "http://192.168.2.14:8080"
    private const val BASE_URL = "http://192.168.178.202:8080"
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}