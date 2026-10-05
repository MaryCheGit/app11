package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.presentation.MainViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Получаем ViewModel
                    val viewModel: MainViewModel = viewModel()
                    val statusText by viewModel.uiState.collectAsState()

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Статус: $statusText",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 20.dp)
                        )

                        Text(
                            text = "1. Загрузить данные (Mock)",
                            fontSize = 20.sp,
                            modifier = Modifier.clickable { viewModel.loadData() }
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        Text(
                            text = "2. Запустить Worker",
                            fontSize = 20.sp,
                            modifier = Modifier.clickable { viewModel.startWorker() }
                        )
                    }
                }
            }
        }
    }
}