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
import com.rishabh.news.util.Util
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var newsList: MutableList<TopHeadlines>
    private lateinit var newsClickObserver: SingleObserver<TopHeadlines>

    init {
        newsList = ArrayList()
    }

    fun setObserver(newsClickObserver: SingleObserver<TopHeadlines>) {
        this.newsClickObserver = newsClickObserver
    }

    fun notifyData(newsList: MutableList<TopHeadlines>) {
        this.newsList.clear()
        this.newsList.addAll(newsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsViewHolder).onBind(newsList.get(position), newsClickObserver)
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var image: ImageView
        private var title: TextView

        init {
            image = itemView.findViewById(R.id.news_image)
            title = itemView.findViewById(R.id.title)
        }

        fun onBind(topHeadlines: TopHeadlines, newsClickObserver: SingleObserver<TopHeadlines>) {
            image.setOnClickListener {

                Single.create<TopHeadlines> { emitter ->
                    if (!emitter.isDisposed) {
                        emitter.onSuccess(topHeadlines)
                    }

                }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                    .subscribe(newsClickObserver)
            }
            title.text = topHeadlines.title
            Util.loadGlideImage(image.context, imageView = image, url = topHeadlines.urlToImage)
        }
    }
}