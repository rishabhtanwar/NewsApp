package com.rishabh.news.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.rishabh.news.model.TopHeadlines

class DetailNewsViewModel constructor(topHeadlines: TopHeadlines) : ViewModel() {
    var topHeadlinesObservableField: ObservableField<TopHeadlines> = ObservableField()

    init {
        topHeadlinesObservableField.set(topHeadlines)
    }
}