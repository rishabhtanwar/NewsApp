package com.rishabh.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rishabh.news.R
import com.rishabh.news.model.TopHeadlines

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var newsList: MutableList<TopHeadlines>

    init {
        newsList = ArrayList<TopHeadlines>()
    }

    fun notifyData(newsList: MutableList<TopHeadlines>) {
        this.newsList.clear()
        this.newsList.addAll(newsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsViewHolder).onBind(newsList.get(position))
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var image: ImageView
        private var title: TextView

        init {
            image = itemView.findViewById(R.id.news_image)
            title = itemView.findViewById(R.id.title)
        }

        fun onBind(topHeadlines: TopHeadlines) {
            title.text = topHeadlines.title
            Glide.with(image.context).load(topHeadlines.urlToImage).into(image)
        }
    }
}