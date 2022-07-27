package com.yashjain.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.yashjain.newsapp.adapters.viewPagerAdapter
import com.yashjain.newsapp.databinding.ActivityMainBinding
import com.yashjain.newsapp.db.ArticleDatabase
import com.yashjain.newsapp.repository.NewsRepository
import com.yashjain.newsapp.ui.fragments.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpTabs()
        val repo=NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(repo)

        viewModel = ViewModelProvider(this,viewModelProviderFactory)[NewsViewModel::class.java]
    }

    private fun setUpTabs() {
        val adapter=viewPagerAdapter(supportFragmentManager)
        adapter.addFragment(trendingFragment(),"Trending")
        adapter.addFragment(politicsFragment(),"Politics")
        adapter.addFragment(sportsFragment(),"Sports")
        adapter.addFragment(informationTechFragment(),"IT")
        adapter.addFragment(entertainmentFragment(),"Entertainment")
        binding.viewPager.adapter= adapter;
        binding.tabs.setupWithViewPager(binding.viewPager)

    }
}