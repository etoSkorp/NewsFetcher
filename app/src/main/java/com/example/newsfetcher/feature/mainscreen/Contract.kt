package com.example.newsfetcher.feature.mainscreen

import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticleModel

data class ViewState(
    val articles: List<ArticleModel>
)

sealed class DataEvent : Event {
    object LoadArticles : DataEvent()
    data class OnLoadArticlesSucceed(val articles: List<ArticleModel>) : DataEvent()
}