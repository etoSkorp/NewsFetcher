package com.example.newsfetcher.feature.mainscreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.bookmark.domain.BookmarksInteractor
import com.example.newsfetcher.feature.domain.ArticlesInteractor
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val articlesInteractor: ArticlesInteractor,
    private val bookmarksInteractor: BookmarksInteractor
) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadArticles)
    }

    override fun initialViewState(): ViewState = ViewState(articles = emptyList())

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
                return previousState.copy(articles = event.articles)
            }
            is UIEvent.OnArticleClicked -> {
                viewModelScope.launch {
                    bookmarksInteractor.create(previousState.articles[event.index])
                }
                return null
            }
            else -> return null
        }
    }
}