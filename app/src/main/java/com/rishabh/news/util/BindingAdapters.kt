package com.rishabh.news.util

import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rishabh.news.adapter.NewsAdapter
import com.rishabh.news.util.Constants
import java.util.*


@BindingAdapter("app:setNewsAdapter")
fun bindNewsAdapter(recycerView: RecyclerView?, newsAdapter: NewsAdapter) {
    newsAdapter.let {
        recycerView?.adapter = newsAdapter
    }
}

@BindingAdapter("app:loadImage")
fun loadImage(imageView: ImageView, url: String) {
    Util.loadGlideImage(imageView.context, imageView, url)
}

@BindingAdapter("app:setDate")
fun setDate(textView: TextView, dateString: String) {
    val date: Date? = Util.serializeDateFromString(dateString, Constants.DATE_FORMATE_FROM_API)
    val deserializeDate: String? = Util.deserializeDate(date, Constants.DATE_FORMAT)
    deserializeDate?.let {
        textView.setText(deserializeDate)
    }
}