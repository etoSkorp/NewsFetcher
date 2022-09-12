package com.example.newsfetcher.feature.bookmark.ui

import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel

data class ViewState(
    val bookmarksArticle: List<ArticleModel>
)

sealed class UIEvent : Event {

}

sealed class DataEvent : Event {

    object LoadBookmarks : DataEvent()
    data class OnSuccessBookmarksLoaded(val bookmarksArticle: List<ArticleModel>) : DataEvent()
    data class OnFailureBookmarksLoaded(val throwable: Throwable) : DataEvent()
}