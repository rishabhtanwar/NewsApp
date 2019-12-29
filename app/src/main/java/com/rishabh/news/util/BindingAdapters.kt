package com.funtoolearn.util

import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rishabh.news.adapter.NewsAdapter


@BindingAdapter("app:setNewsAdapter")
fun bindNewsAdapter(recycerView: RecyclerView?, newsAdapter: NewsAdapter) {
    newsAdapter?.let {
        recycerView?.adapter = newsAdapter
    }
}
