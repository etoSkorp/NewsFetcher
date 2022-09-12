package com.example.newsfetcher.feature.bookmark.data

import com.example.newsfetcher.feature.bookmark.data.local.model.BookmarkEntity
import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel

fun BookmarkEntity.toDomain() = ArticleModel(
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt
)

fun ArticleModel.toEntity() = BookmarkEntity(
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt
)