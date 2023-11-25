package com.example.retrofitcrud.data

import com.example.retrofitcrud.data.repository.UsersRepository
import com.example.retrofitcrud.data.retrofit.RetrofitBuilder

interface AppContainer {
    val usersRepository: UsersRepository
}

class AppDataContainer : AppContainer {
    override val usersRepository = UsersRepository(RetrofitBuilder.apiService)
}
