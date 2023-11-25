package com.example.retrofitcrud

import android.app.Application
import com.example.retrofitcrud.data.AppDataContainer

class RetrofitCRUDApplication : Application() {
    lateinit var container: AppDataContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
