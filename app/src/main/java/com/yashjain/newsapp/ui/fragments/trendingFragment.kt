package com.yashjain.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yashjain.newsapp.R
import com.yashjain.newsapp.adapters.newsAdapter
import com.yashjain.newsapp.ui.MainActivity
import com.yashjain.newsapp.ui.NewsViewModel
import com.yashjain.newsapp.utils.Resource
import kotlinx.android.synthetic.main.all_fragment_item.*
import java.util.zip.Inflater

class trendingFragment:Fragment(R.layout.fragment_trending) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: newsAdapter

    val TAG= "TrendingNewsFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).viewModel
        setUpRecyclerview()

        viewModel.trendingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse?.articles)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG,"ERROR: $message")
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar(){
    paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
    paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setUpRecyclerview(){
        newsAdapter = newsAdapter()
        rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}