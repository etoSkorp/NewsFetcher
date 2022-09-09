package com.example.newsfetcher.feature.bookmark.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private val viewModel: BookmarksScreenViewModel by viewModel()
    private val recyclerView: RecyclerView by lazy { requireActivity().findViewById(R.id.rvBookmarkedArticles) }
    private val adapter: BookmarksAdapter by lazy { BookmarksAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        recyclerView.adapter = adapter
    }

    private fun render(viewState: ViewState) {
        adapter.setData(viewState.bookmarksArticle)
    }
}