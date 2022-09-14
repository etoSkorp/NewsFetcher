package com.example.newsfetcher.feature.mainscreen.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private val viewModel: MainScreenViewModel by viewModel()
    private val recyclerView: RecyclerView by lazy { requireActivity().findViewById(R.id.rvArticles) }
    private val ivSearch: ImageView by lazy { requireActivity().findViewById(R.id.ivSearch) }
    private val tvMainTitle: TextView by lazy { requireActivity().findViewById(R.id.tvMainTitle) }
    private val etSearch: EditText by lazy { requireActivity().findViewById(R.id.etSearch) }
    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter(
            { index -> viewModel.processUIEvent(UIEvent.OnBookmarkIconClicked(index)) },
            { expandNews -> showNews(expandNews) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        recyclerView.adapter = adapter

        ivSearch.setOnClickListener {
            etSearch.setText("")
            viewModel.processUIEvent(UIEvent.OnSearchButtonClicked)
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                text: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                val textLowerCase = text.toString().lowercase()
                viewModel.processUIEvent(UIEvent.OnSearchEditTextInput(textLowerCase))
            }

        })
    }

    private fun render(viewState: ViewState) {
        tvMainTitle.isVisible = !viewState.isSearchEnabled
        etSearch.isVisible = viewState.isSearchEnabled
        etSearch.requestFocus()
        adapter.setData(viewState.articlesShown)
    }

    private fun showNews(news: ArticleModel) {
        val bundle = Bundle()
        bundle.putString("title", news.title)
        bundle.putString("url", news.url)
        bundle.putString("description", news.description)
        bundle.putString("publishedAt", news.publishedAt)
        bundle.putString("urlToImage", news.urlToImage)
        parentFragmentManager.beginTransaction().replace(R.id.container, NewsFragment(bundle))
            .addToBackStack(null).commit()
    }
}