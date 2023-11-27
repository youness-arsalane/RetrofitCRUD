package com.example.retrofitcrud.ui.viewmodel.users

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitcrud.data.model.User
import com.example.retrofitcrud.data.repository.UsersRepository
import com.example.retrofitcrud.data.retrofit.RetrofitBuilder
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserListViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {
    val userListUiState: StateFlow<UserListUiState> =
        usersRepository.users
            .map { UserListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = UserListUiState()
            )

    init {
        viewModelScope.launch {
            usersRepository.getUsers()
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class UserListUiState(val userList: List<User> = listOf())
