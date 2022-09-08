package com.example.newsfetcher.feature.data

import com.example.newsfetcher.feature.data.model.ArticlesRemoteModel

class ArticlesRemoteSource(private val newsApi: NewsApi) {

    suspend fun getArticles(): ArticlesRemoteModel {
        return newsApi.getArticles()
    }
}