package com.example.newsfetcher.feature.mainscreen.data

import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel

class ArticlesRepositoryImpl(private val articlesRemoteSource: ArticlesRemoteSource) :
    ArticlesRepository {
    override suspend fun getArticles(): List<ArticleModel> {
        return articlesRemoteSource.getArticles().articleList.map {
            it.toDomain()
        }
    }
}