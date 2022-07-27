package com.yashjain.newsapp.repository

import com.yashjain.newsapp.api.RetrofitInstance
import com.yashjain.newsapp.db.ArticleDatabase
import retrofit2.Retrofit

class NewsRepository(
    val db:ArticleDatabase
) {
    suspend fun getNews(searchQuery:String,countryCode:String,pageNo:Int)=
        RetrofitInstance.api.getNews(searchQuery,pageNo)
}