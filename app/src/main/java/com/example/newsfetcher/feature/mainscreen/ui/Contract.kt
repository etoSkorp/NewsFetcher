package com.example.newsfetcher.feature.mainscreen.ui

import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel

data class ViewState(
    val isSearchEnabled: Boolean,
    val articlesList: List<ArticleModel>,
    val articlesShown: List<ArticleModel>
)

sealed class UIEvent : Event {
    data class OnBookmarkIconClicked(val index: Int) : UIEvent()
    object OnSearchButtonClicked : UIEvent()
    data class OnSearchEditTextInput(val textLowerCase: String) : UIEvent()
}

sealed class DataEvent : Event {
    object LoadArticles : DataEvent()
    data class OnLoadArticlesSucceed(val articlesList: List<ArticleModel>) : DataEvent()
}