package com.example.retrofitcrud.data.repository

import com.example.retrofitcrud.data.IApiService
import com.example.retrofitcrud.data.model.User

class UsersRepository(private val apiService: IApiService) {
    suspend fun getUsers(): List<User> = apiService.getUsers()

    suspend fun getUser(id: Int): User = apiService.getUser(id)

    suspend fun insertUser(user: User): User = apiService.insertUser(user)

    suspend fun updateUser(user: User): User = apiService.updateUser(user.id, user)

    suspend fun deleteUser(user: User) = apiService.deleteUser(user.id)
}
