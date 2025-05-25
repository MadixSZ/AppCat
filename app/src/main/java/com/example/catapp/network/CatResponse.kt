package com.example.catapp.network

data class CatResponse(
    val url: String?,
    val tags: List<String>?,
    val createdAt: String?,
    val updatedAt: String?
)