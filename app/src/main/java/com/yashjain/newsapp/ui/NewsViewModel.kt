package com.yashjain.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yashjain.newsapp.POJO.newsResponse
import com.yashjain.newsapp.repository.NewsRepository
import com.yashjain.newsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository : NewsRepository
) : ViewModel() {
    val trendingNews: MutableLiveData<Resource<newsResponse>> = MutableLiveData()
    val politicalNews: MutableLiveData<Resource<newsResponse>> = MutableLiveData()
    val sportsNews: MutableLiveData<Resource<newsResponse>> = MutableLiveData()
    val itNews: MutableLiveData<Resource<newsResponse>> = MutableLiveData()
    val entertainmentNews: MutableLiveData<Resource<newsResponse>> = MutableLiveData()
    var pageNumber = 1

    init {
        getTrendingNews("trending","in")
        getPoliticalNews("politics","in")
        getSportsNews("sports","in")
        getItNews("IT","in")
        getEntertainmentNews("entertainment","in")
    }

    private fun getTrendingNews(searchQuery:String, countryCode: String) =  viewModelScope.launch {
        trendingNews.postValue(Resource.Loading())
        val response = newsRepository.getNews(searchQuery,countryCode,pageNumber)
        trendingNews.postValue(handleNewsResponse(response))
    }

    private fun getPoliticalNews(searchQuery:String, countryCode: String) =  viewModelScope.launch {
        politicalNews.postValue(Resource.Loading())
        val response = newsRepository.getNews(searchQuery,countryCode,pageNumber)
        politicalNews.postValue(handleNewsResponse(response))
    }

    private fun getSportsNews(searchQuery:String, countryCode: String) =  viewModelScope.launch {
        sportsNews.postValue(Resource.Loading())
        val response = newsRepository.getNews(searchQuery,countryCode,pageNumber)
        sportsNews.postValue(handleNewsResponse(response))
    }

    private fun getItNews(searchQuery:String, countryCode: String) =  viewModelScope.launch {
        itNews.postValue(Resource.Loading())
        val response = newsRepository.getNews(searchQuery,countryCode,pageNumber)
        itNews.postValue(handleNewsResponse(response))
    }

    private fun getEntertainmentNews(searchQuery:String, countryCode: String) =  viewModelScope.launch {
        entertainmentNews.postValue(Resource.Loading())
        val response = newsRepository.getNews(searchQuery,countryCode,pageNumber)
        entertainmentNews.postValue(handleNewsResponse(response))
    }

    private fun handleNewsResponse(response: Response<newsResponse>): Resource<newsResponse>{
        if(response.isSuccessful){
            response.body()?.let {  resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }
}