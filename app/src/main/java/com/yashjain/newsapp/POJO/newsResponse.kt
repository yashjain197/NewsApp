package com.yashjain.newsapp.POJO

data class newsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)