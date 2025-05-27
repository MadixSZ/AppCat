package com.example.catapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catapp.repository.CatRepository

@Suppress("UNCHECKED_CAST")
class CatViewModelFactory(private val repository: CatRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CatViewModel(repository) as T
    }
}