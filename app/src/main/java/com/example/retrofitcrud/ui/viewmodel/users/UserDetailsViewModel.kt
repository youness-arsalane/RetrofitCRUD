package com.example.retrofitcrud.ui.viewmodel.users

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitcrud.data.AppDataContainer
import com.example.retrofitcrud.data.model.User
import com.example.retrofitcrud.ui.screens.users.UserDetailsDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class UserDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val appContainer: AppDataContainer
) : ViewModel() {

    private val userId: Int = checkNotNull(savedStateHandle[UserDetailsDestination.userIdArg])

    val uiState: StateFlow<UserDetailsUiState> =
        flow { emit(appContainer.usersRepository.getUser(userId)) }
            .map { UserDetailsUiState(userDetails = it.toUserDetails()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = UserDetailsUiState()
            )

    suspend fun deleteUser() {
        appContainer.usersRepository.deleteUser(uiState.value.userDetails.toUser())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class UserDetailsUiState(
    val userDetails: UserDetails = UserDetails()
)