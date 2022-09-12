package com.example.newsfetcher.feature.bookmark.domain

import com.example.newsfetcher.base.Either
import com.example.newsfetcher.base.attempt
import com.example.newsfetcher.feature.bookmark.data.local.BookmarksRepository
import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel

class BookmarksInteractor(private val bookmarksRepository: BookmarksRepository) {
    suspend fun create(model: ArticleModel) {
        attempt { bookmarksRepository.create(model) }
    }

    suspend fun read(): Either<Throwable, List<ArticleModel>> {
        return attempt { bookmarksRepository.read() }
    }

    suspend fun update(model: ArticleModel) {
        attempt { bookmarksRepository.update(model) }
    }

    suspend fun delete(model: ArticleModel) {
        attempt { bookmarksRepository.delete(model) }
    }
}