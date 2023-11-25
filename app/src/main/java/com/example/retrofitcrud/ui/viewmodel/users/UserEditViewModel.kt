package com.example.retrofitcrud.ui.viewmodel.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitcrud.data.AppDataContainer
import com.example.retrofitcrud.ui.screens.users.UserEditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UserEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val appContainer: AppDataContainer
) : ViewModel() {
    var userUiState by mutableStateOf(UserUiState())
        private set

    private val userId: Int =
        checkNotNull(savedStateHandle[UserEditDestination.userIdArg])

    init {
        viewModelScope.launch {
            userUiState = appContainer.usersRepository.getUser(userId)
                .toUserUiState(true)
        }
    }

    suspend fun updateUser() {
        if (validateInput(userUiState.userDetails)) {
            appContainer.usersRepository.updateUser(userUiState.userDetails.toUser())
        }
    }

    fun updateUiState(userDetails: UserDetails) {
        userUiState =
            UserUiState(
                userDetails = userDetails,
                isEntryValid = validateInput(userDetails)
            )
    }

    private fun validateInput(uiState: UserDetails = userUiState.userDetails): Boolean {
        return with(uiState) {
            email.isNotBlank() && firstName.isNotBlank() && lastName.isNotBlank()
        }
    }
}
