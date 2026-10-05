package com.example.myapplication.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.WorkProgress
import com.example.myapplication.data.DataRepository
import com.example.myapplication.domain.GetDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DataRepository(application.applicationContext)
    private val getDataUseCase = GetDataUseCase(repository)

    private val _uiState = MutableStateFlow("Нажмите кнопку для загрузки")
    val uiState: StateFlow<String> = _uiState

    init {
        // Подписываемся на прогресс из Worker
        viewModelScope.launch {
            WorkProgress.status.collect { workerStatus ->
                _uiState.value = workerStatus
            }
        }
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = "Загрузка..."
            val result = getDataUseCase()
            _uiState.value = result
        }
    }

    fun startWorker() {
        repository.startBackgroundWork()
        // Статус обновится автоматически через WorkProgress
    }
}