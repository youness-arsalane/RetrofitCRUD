package com.example.retrofitcrud.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.retrofitcrud.ui.navigation.RetrofitCRUDNavHost

@Composable
fun RetrofitCRUDApp(navController: NavHostController = rememberNavController()) {
    RetrofitCRUDNavHost(navController = navController)
}