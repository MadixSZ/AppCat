package com.example.catapp.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.catapp.database.AppDatabase
import com.example.catapp.model.CatImage
import com.example.catapp.network.RetrofitClient
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CatRepository(context: Context) {
    private val catImageDao = AppDatabase.getDatabase(context).catImageDao()
    private val apiService = RetrofitClient.instance

    fun getAllCats(): LiveData<List<CatImage>> = catImageDao.getAllCats()

    suspend fun fetchNewCat() {
        try {
            val response = apiService.getRandomCat()
            val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

            val catImage = CatImage(
                imageUrl = "https://cataas.com${response.url ?: ""}",
                createdAt = response.createdAt ?: currentTime
            )
            catImageDao.insert(catImage)
            Log.d("DEBUG", "Imagem salva: ${catImage.imageUrl}")
        } catch (e: Exception) {
            Log.e("ERROR", "Erro: ${e.message}")
        }
    }
}