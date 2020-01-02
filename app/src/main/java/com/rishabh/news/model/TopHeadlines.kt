package com.rishabh.news.model

import java.io.Serializable

data class TopHeadlines(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) : Serializable