package com.example.newsfetcher.feature.mainscreen.domain

import com.example.newsfetcher.base.attempt
import com.example.newsfetcher.feature.mainscreen.data.ArticlesRepository

class ArticlesInteractor(private val articlesRepository: ArticlesRepository) {

    suspend fun getArticles() = attempt { articlesRepository.getArticles() }
}