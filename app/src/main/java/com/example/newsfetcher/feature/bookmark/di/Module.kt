package com.example.newsfetcher.feature.bookmark.di

import com.example.newsfetcher.feature.bookmark.data.local.BookmarksLocalSource
import com.example.newsfetcher.feature.bookmark.data.local.BookmarksRepository
import com.example.newsfetcher.feature.bookmark.data.local.BookmarksRepositoryImpl
import com.example.newsfetcher.feature.bookmark.domain.BookmarksInteractor
import com.example.newsfetcher.feature.bookmark.ui.BookmarksScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarksModule = module {

    single<BookmarksLocalSource> {
        BookmarksLocalSource(bookmarksDao = get())
    }

    single<BookmarksRepository> {
        BookmarksRepositoryImpl(bookmarksLocalSource = get())
    }

    single<BookmarksInteractor> {
        BookmarksInteractor(bookmarksRepository = get())
    }

    viewModel {
        BookmarksScreenViewModel(bookmarksInteractor = get())
    }
}