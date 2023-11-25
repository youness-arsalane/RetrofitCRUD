package com.example.retrofitcrud.ui.viewmodel.users

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitcrud.data.AppDataContainer
import com.example.retrofitcrud.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserListViewModel(private val appContainer: AppDataContainer) : ViewModel() {
//    val userListUiState: StateFlow<UserListUiState> =
//        flow { emit(appContainer.usersRepository.getUsers()) }
//            .map { UserListUiState(it) }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                initialValue = UserListUiState()
//            )
    val userListUiState: StateFlow<UserListUiState> =
        appContainer.usersRepository.users
            .map { UserListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = UserListUiState()
            )

    init {
        Log.d("HOOD_ALERT_DEBUG", "UserListViewModel")
        viewModelScope.launch {
            appContainer.usersRepository.getUsers()
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class UserListUiState(val userList: List<User> = listOf())
