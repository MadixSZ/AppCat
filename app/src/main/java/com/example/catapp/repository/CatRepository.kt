package com.example.catapp.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.catapp.database.AppDatabase
import com.example.catapp.model.CatImage
import com.example.catapp.network.RetrofitClient

class CatRepository(context: Context) {
    private val catImageDao = AppDatabase.getDatabase(context).catImageDao()
    private val apiService = RetrofitClient.instance

    suspend fun fetchNewCat() {
        try {
            val response = apiService.getRandomCat()
            val catImage = CatImage(
                imageUrl = "https://cataas.com${response.url}",
                createdAt = response.createdAt ?: ""
            )
            catImageDao.insert(catImage)
        } catch (e: Exception) {
            Log.e("API_ERROR", "Erro: ${e.message}")
        }
    }

}