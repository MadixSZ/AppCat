package com.example.catapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.catapp.model.CatImage

@Dao
interface CatImageDao {
    @Insert
    suspend fun insert(catImage: CatImage)

    @Query("SELECT * FROM cat_images ORDER BY createdAt DESC")
    fun getAllCats(): LiveData<List<CatImage>>
}