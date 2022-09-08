package com.example.newsfetcher.feature.data

import com.example.newsfetcher.feature.domain.ArticleModel

class ArticlesRepositoryImpl(private val articlesRemoteSource: ArticlesRemoteSource) :
    ArticlesRepository {
    override suspend fun getArticles(): List<ArticleModel> {
        return articlesRemoteSource.getArticles().articleList.map {
            it.toDomain()
        }
    }
}