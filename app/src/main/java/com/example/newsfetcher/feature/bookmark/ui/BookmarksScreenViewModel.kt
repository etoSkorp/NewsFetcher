package com.example.newsfetcher.feature.bookmark.ui

import androidx.lifecycle.viewModelScope
import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.bookmark.domain.BookmarksInteractor
import kotlinx.coroutines.launch

class BookmarksScreenViewModel(private val bookmarksInteractor: BookmarksInteractor) :
    BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadBookmarks)
    }

    override fun initialViewState(): ViewState = ViewState(bookmarksArticle = emptyList())

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.LoadBookmarks -> {
                viewModelScope.launch {
                    bookmarksInteractor.read().fold(
                        onError = {
                            processDataEvent(DataEvent.OnFailureBookmarksLoaded(it))
                        },
                        onSuccess = {
                            processDataEvent(DataEvent.OnSuccessBookmarksLoaded(it))
                        }
                    )
                }
                return null
            }
            is DataEvent.OnSuccessBookmarksLoaded -> {
                return previousState.copy(bookmarksArticle = event.bookmarksArticle)
            }
            else -> return null
        }
    }
}