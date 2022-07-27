package com.yashjain.newsapp.api

import com.yashjain.newsapp.POJO.newsResponse
import com.yashjain.newsapp.utils.constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface newsAPI {
    @GET("v2/everything")
    suspend fun getNews(
        @Query("q")
        searchQuery:String,
//        @Query("country")
//        countryCode: String="in",
        @Query("page")
        pageNumber:Int=1,
        @Query("apiKey")
        apiKey:String=API_KEY
    ):Response<newsResponse>


}