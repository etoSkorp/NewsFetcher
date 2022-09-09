package com.example.newsfetcher.feature.di

import com.example.newsfetcher.feature.bookmark.domain.BookmarksInteractor
import com.example.newsfetcher.feature.data.ArticlesRemoteSource
import com.example.newsfetcher.feature.data.ArticlesRepository
import com.example.newsfetcher.feature.data.ArticlesRepositoryImpl
import com.example.newsfetcher.feature.data.NewsApi
import com.example.newsfetcher.feature.domain.ArticlesInteractor
import com.example.newsfetcher.feature.mainscreen.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val mainScreenModule = module {

    single<NewsApi> {
        get<Retrofit>().create(NewsApi::class.java)
    }

    single<ArticlesRemoteSource> { ArticlesRemoteSource(newsApi = get<NewsApi>()) }

    single<ArticlesRepository> { ArticlesRepositoryImpl(articlesRemoteSource = get<ArticlesRemoteSource>()) }

    single<ArticlesInteractor> { ArticlesInteractor(articlesRepository = get<ArticlesRepository>()) }

    viewModel {
        MainScreenViewModel(
            articlesInteractor = get<ArticlesInteractor>(),
            bookmarksInteractor = get<BookmarksInteractor>()
        )
    }
}