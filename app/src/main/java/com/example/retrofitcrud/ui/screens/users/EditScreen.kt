package com.example.retrofitcrud.ui.screens.users

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofitcrud.R
import com.example.retrofitcrud.ui.AppViewModelProvider
import com.example.retrofitcrud.ui.components.DefaultTopAppBar
import com.example.retrofitcrud.ui.navigation.NavigationDestination
import com.example.retrofitcrud.ui.theme.RetrofitCRUDTheme
import com.example.retrofitcrud.ui.viewmodel.users.UserEditViewModel
import kotlinx.coroutines.launch

object UserEditDestination : NavigationDestination {
    override val route = "user_edit"
    override val titleRes = R.string.edit_user_title
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UserEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            DefaultTopAppBar(
                title = stringResource(UserEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryBody(
            userUiState = viewModel.userUiState,
            onUserValueChange = { viewModel.updateUiState(it) },
            onSaveClick = {
                coroutineScope.launch {
                    try {
                        viewModel.updateUser()
                        navigateBack()
                    } catch (e: Exception) {
                        Log.e("RetrofitCRUD", "exception", e)
                        Toast.makeText(context, "An unknown error occurred", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditScreenPreview() {
    RetrofitCRUDTheme {
        EditScreen(navigateBack = { /*Do nothing*/ }, onNavigateUp = { /*Do nothing*/ })
    }
}
