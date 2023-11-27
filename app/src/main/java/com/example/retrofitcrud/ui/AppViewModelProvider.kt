package com.example.retrofitcrud.ui

import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.retrofitcrud.data.repository.UsersRepository
import com.example.retrofitcrud.data.retrofit.RetrofitBuilder
import com.example.retrofitcrud.ui.viewmodel.users.UserDetailsViewModel
import com.example.retrofitcrud.ui.viewmodel.users.UserEditViewModel
import com.example.retrofitcrud.ui.viewmodel.users.UserEntryViewModel
import com.example.retrofitcrud.ui.viewmodel.users.UserListViewModel

object AppViewModelProvider {
    private val usersRepository = UsersRepository(RetrofitBuilder.apiService)

    val Factory = viewModelFactory {
        initializer {
            UserEditViewModel(this.createSavedStateHandle(), usersRepository)
        }

        initializer {
            UserEntryViewModel(usersRepository)
        }

        initializer {
            UserDetailsViewModel(this.createSavedStateHandle(), usersRepository)
        }

        initializer {
            UserListViewModel(usersRepository)
        }
    }
}
