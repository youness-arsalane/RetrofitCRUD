package com.example.retrofitcrud.data

import android.content.Context
import com.example.retrofitcrud.data.repository.UsersRepository

interface AppContainer {
    val usersRepository: UsersRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val usersRepository = UsersRepository(apiService)
}
