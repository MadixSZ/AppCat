package com.example.catapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.repository.CatRepository
import kotlinx.coroutines.launch

class CatViewModel(private val repository: CatRepository) : ViewModel() {

    val allCats = repository.getAllCats()

    fun fetchNewCat() {
        viewModelScope.launch {
            repository.fetchNewCat()
        }
    }
}