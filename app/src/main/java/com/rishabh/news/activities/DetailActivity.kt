package com.rishabh.news.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rishabh.news.R
import com.rishabh.news.databinding.ActivityDetailBinding
import com.rishabh.news.model.TopHeadlines
import com.rishabh.news.viewmodels.DetailNewsViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var detailNewsViewModel: DetailNewsViewModel
    private lateinit var topHeadlines: TopHeadlines

    companion object {
        private const val EXTRAS_NEWS_DATA = "news_data"

        fun getStartIntent(context: Context, topHeadlines: TopHeadlines?) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRAS_NEWS_DATA, topHeadlines)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        topHeadlines = intent?.extras?.getSerializable(EXTRAS_NEWS_DATA) as TopHeadlines
        detailNewsViewModel = DetailNewsViewModel(topHeadlines)
        activityDetailBinding.detailNewsViewModel = detailNewsViewModel
    }
}
