package com.example.newsfetcher.feature.mainscreen.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.bookmark.domain.BookmarksInteractor
import com.example.newsfetcher.feature.mainscreen.domain.ArticlesInteractor
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val articlesInteractor: ArticlesInteractor,
    private val bookmarksInteractor: BookmarksInteractor
) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadArticles)
    }

    override fun initialViewState(): ViewState = ViewState(
        articlesList = emptyList(),
        articlesShown = emptyList(),
        isSearchEnabled = false
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.LoadArticles -> {
                viewModelScope.launch {
                    articlesInteractor.getArticles().fold(
                        onError = {
                            Log.e("ERROR", it.localizedMessage)
                        },
                        onSuccess = {
                            processDataEvent(DataEvent.OnLoadArticlesSucceed(it))
                        }
                    )
                }
                return null
            }
            is DataEvent.OnLoadArticlesSucceed -> {
                return previousState.copy(
                    articlesList = event.articlesList,
                    articlesShown = event.articlesList
                )
            }
            is UIEvent.OnBookmarkIconClicked -> {
                viewModelScope.launch {
                    bookmarksInteractor.create(previousState.articlesShown[event.index])
                }
                return null
            }
            is UIEvent.OnSearchButtonClicked -> {
                return previousState.copy(
                    articlesShown = if (previousState.isSearchEnabled) previousState.articlesList else previousState.articlesShown,
                    isSearchEnabled = !previousState.isSearchEnabled
                )
            }
            is UIEvent.OnSearchEditTextInput -> {
                return previousState.copy(
                    articlesShown = previousState.articlesList.filter {
                        it.title.lowercase().contains(event.textLowerCase)
                    })
            }
            else -> return null
        }
    }
}