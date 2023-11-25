package com.example.retrofitcrud.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.retrofitcrud.RetrofitCRUDApplication
import com.example.retrofitcrud.ui.viewmodel.users.UserDetailsViewModel
import com.example.retrofitcrud.ui.viewmodel.users.UserEditViewModel
import com.example.retrofitcrud.ui.viewmodel.users.UserEntryViewModel
import com.example.retrofitcrud.ui.viewmodel.users.UserListViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            UserEditViewModel(
                this.createSavedStateHandle(),
                retrofitCRUDApplication().container
            )
        }

        initializer {
            UserEntryViewModel(retrofitCRUDApplication().container)
        }

        initializer {
            UserDetailsViewModel(
                this.createSavedStateHandle(),
                retrofitCRUDApplication().container
            )
        }

        initializer {
            UserListViewModel(retrofitCRUDApplication().container)
        }
    }
}

fun CreationExtras.retrofitCRUDApplication(): RetrofitCRUDApplication {
    return (this[AndroidViewModelFactory.APPLICATION_KEY] as RetrofitCRUDApplication)
}
