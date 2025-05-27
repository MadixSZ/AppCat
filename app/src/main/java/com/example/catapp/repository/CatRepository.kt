package com.example.catapp.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.catapp.database.AppDatabase
import com.example.catapp.model.CatImage
import com.example.catapp.network.RetrofitClient
import java.text.SimpleDateFormat
import java.util.*

class CatRepository(context: Context) {
    private val catImageDao = AppDatabase.getDatabase(context).catImageDao()
    private val apiService = RetrofitClient.instance

    fun getAllCats(): LiveData<List<CatImage>> = catImageDao.getAllCats()

    suspend fun fetchNewCat() {
        try {
            val response = apiService.getRandomCat()
            val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

            val fullUrl = if (response.url?.startsWith("/") == true) {
                "https://cataas.com${response.url}"
            } else {
                response.url ?: ""
            }

            val catImage = CatImage(
                imageUrl = fullUrl,
                createdAt = response.createdAt ?: currentTime
            )

            catImageDao.insert(catImage)
            Log.d("CatRepository", "Nova imagem salva: $fullUrl")
        } catch (e: Exception) {
            Log.e("CatRepository", "Erro ao buscar gato: ${e.message}")
            throw e
        }
    }
}