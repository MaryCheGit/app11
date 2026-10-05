package com.example.myapplication.domain

import com.example.myapplication.data.DataRepository

class GetDataUseCase(private val repository: DataRepository) {
    suspend operator fun invoke(): String {

        return repository.fetchData()
    }
}