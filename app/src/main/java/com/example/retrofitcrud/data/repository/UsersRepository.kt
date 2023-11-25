package com.example.retrofitcrud.data.repository

import android.util.Log
import com.example.retrofitcrud.data.retrofit.ApiService
import com.example.retrofitcrud.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsersRepository(private val apiService: ApiService) {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _userDetails = MutableStateFlow<User?>(null)
    val userDetails: StateFlow<User?> = _userDetails.asStateFlow()

    suspend fun getUsers(): List<User> {
        val users = apiService.getUsers()
        _users.value = users
        return users
    }

    suspend fun getUser(id: Int): User {
        Log.d("HOOD_ALERT_DEBUG", "ID: $id")
        val user = apiService.getUser(id)
        _userDetails.value = user
        return user
    }

    suspend fun insertUser(user: User): User {
        val result = apiService.insertUser(user)
        _users.value = _users.value + result
        _userDetails.value = result
        return result
    }

    suspend fun updateUser(user: User): User {
        val result = apiService.updateUser(user.id, user)
        _users.value = _users.value.map { if (it.id == result.id) result else it }
        _userDetails.value = result
        return result
    }

    suspend fun deleteUser(user: User) {
        Log.d("HOOD_ALERT_DEBUG", "deleteUser")
        apiService.deleteUser(user.id)
        Log.d("HOOD_ALERT_DEBUG", "deleted User!")
        Log.d("HOOD_ALERT_DEBUG", "currentCount: ${_users.value.count()}")
        _users.value = _users.value.filter { it.id != user.id }
        Log.d("HOOD_ALERT_DEBUG", "newCount: ${_users.value.count()}")
    }
}
