package com.example.catapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_images")
data class CatImage(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val imageUrl: String = "",
    val createdAt: String = "" // Mantenha o valor padrão
)