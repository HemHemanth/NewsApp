package com.hemanth.newsapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hemanth.newsapp.api.ApiServices
import com.hemanth.newsapp.api.RetrofitBuilder
import com.hemanth.newsapp.model.Articles
import com.hemanth.newsapp.utils.Status
import com.hemanth.newsapp.utils.Utility
import com.hemanth.newsapp.utils.ViewModelProviderFactory
import com.hemanth.newsapp.views.adapter.ArticlesAdapter
import com.hemanth.newsapp.views.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var apiServices: ApiServices
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var screenType: String
    private var h by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenType = resources.getString(R.string.screen_type)
        getHeight()
        apiServices = RetrofitBuilder.apiServices
        setUpViewModel()
        setUpNewsObserver()
    }

    private fun setUpViewModel() {
        newsViewModel = ViewModelProvider(
            this,
            ViewModelProviderFactory(apiServices)
        )
            .get(NewsViewModel::class.java)
    }

    private fun setUpNewsObserver() {
        newsViewModel.getArticlesList().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    recyclerViewNews.apply {
                        adapter = ArticlesAdapter(this@MainActivity, it.data as ArrayList<Articles>, h)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }

                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun getHeight() {
        if (screenType == "1") {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            h = Utility.convertDpToPixel(300F, this).toInt()
        } else if (screenType == "2") {
            h = Utility.convertDpToPixel(400F, this).toInt()
        } else {
            h = Utility.convertDpToPixel(500F, this).toInt()
        }
    }
}