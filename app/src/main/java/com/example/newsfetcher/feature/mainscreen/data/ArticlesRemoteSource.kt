package com.example.newsfetcher.feature.mainscreen.data

import com.example.newsfetcher.feature.mainscreen.data.model.ArticlesRemoteModel

class ArticlesRemoteSource(private val newsApi: NewsApi) {

    suspend fun getArticles(): ArticlesRemoteModel {
        return newsApi.getArticles()
    }
}