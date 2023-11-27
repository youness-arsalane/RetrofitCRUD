package com.example.retrofitcrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.retrofitcrud.ui.navigation.AppNavHost
import com.example.retrofitcrud.ui.theme.RetrofitCRUDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitCRUDTheme {
                AppNavHost(
                    navController = rememberNavController()
                )
            }
        }
    }
}