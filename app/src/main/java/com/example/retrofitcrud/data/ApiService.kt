package com.example.retrofitcrud.data

import com.example.retrofitcrud.data.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE

interface IApiService {
    @GET("/users")
    suspend fun getUsers(): List<User>

    @GET("/users/{id}")
    suspend fun getUser(id: Int): User

    @POST("/users")
    suspend fun insertUser(@Body user: User): User

    @PUT("/users/{id}")
    suspend fun updateUser(id: Int, @Body user: User): User

    @DELETE("/users/{id}")
    suspend fun deleteUser(id: Int)
}

/* if the Ktor server is hosted locally, use your local IP address */
private const val BASE_URL = "http://192.168.2.14:8080"
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService: IApiService = retrofit.create(IApiService::class.java)
