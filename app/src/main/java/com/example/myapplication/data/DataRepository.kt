package com.example.myapplication.data

import android.content.Context
import com.example.myapplication.MyWorker
import kotlinx.coroutines.delay

class DataRepository(private val context: Context) {


    suspend fun fetchData(): String {
        delay(2000)
        return "Данные успешно загружены из Mock-источника!"
    }

    // Запуск фоновой работы через WorkManager
    fun startBackgroundWork() {
        MyWorker.start(context)
    }
}