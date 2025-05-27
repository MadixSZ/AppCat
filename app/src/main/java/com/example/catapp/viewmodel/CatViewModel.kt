package com.example.catapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.model.CatImage
import com.example.catapp.repository.CatRepository
import kotlinx.coroutines.launch

class CatViewModel(private val repository: CatRepository) : ViewModel() {
    val allCats: LiveData<List<CatImage>> = repository.getAllCats()

    fun fetchNewCat() {
        viewModelScope.launch {
            repository.fetchNewCat()
        }
    }
}