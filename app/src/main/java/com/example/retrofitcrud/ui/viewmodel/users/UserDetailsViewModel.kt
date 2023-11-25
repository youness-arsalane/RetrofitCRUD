package com.example.retrofitcrud.ui.viewmodel.users

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitcrud.data.AppDataContainer
import com.example.retrofitcrud.ui.screens.users.UserDetailsDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val appContainer: AppDataContainer
) : ViewModel() {

    private val userId: Int = checkNotNull(savedStateHandle[UserDetailsDestination.userIdArg])
//    private val _userDetails = MutableStateFlow<User?>(null)
//    val userDetails: StateFlow<User?> = _userDetails



    val uiState: StateFlow<UserDetailsUiState> =
        appContainer.usersRepository.userDetails
            .map {
                if (it !== null) {
                    UserDetailsUiState(userDetails = it.toUserDetails())
                } else {
                    UserDetailsUiState()
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = UserDetailsUiState()
            )

    init {
        viewModelScope.launch {
            Log.d("HOOD_ALERT_DEBUG", "launch1 -> ID: $userId")
            Log.d("HOOD_ALERT_DEBUG", "launch1 -> ID: ${checkNotNull(savedStateHandle[UserDetailsDestination.userIdArg])}")
            appContainer.usersRepository.getUser(userId)
        }
    }

    suspend fun deleteUser() {
        appContainer.usersRepository.deleteUser(uiState.value.userDetails.toUser())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class UserDetailsUiState(
    var userDetails: UserDetails = UserDetails()
)