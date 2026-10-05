package com.example.myapplication

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.flow.MutableStateFlow

// Синглтон для обмена данными между Worker и ViewModel
object WorkProgress {
    val status = MutableStateFlow("Ожидание...")
}

class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        return try {

            WorkProgress.status.value = "Worker: Начало работы..."
            Thread.sleep(2000)

            WorkProgress.status.value = "Worker: Шаг 1 выполнен"
            Thread.sleep(2000)

            WorkProgress.status.value = "Worker: Шаг 2 выполнен"
            Thread.sleep(2000)

            WorkProgress.status.value = "Worker: Все задачи завершены!"
            Result.success()
        } catch (ex: Exception) {
            WorkProgress.status.value = "Worker: Ошибка - ${ex.message}"
            Result.failure()
        }
    }

    companion object {
        fun start(context: Context) {

        }
    }
}
