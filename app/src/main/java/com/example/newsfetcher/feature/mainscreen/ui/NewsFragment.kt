package com.example.newsfetcher.feature.mainscreen.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.newsfetcher.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment(private val bundle: Bundle) : Fragment(R.layout.item_news) {

    private val viewModel: MainScreenViewModel by viewModel()
    private val newsDescription: TextView by lazy { requireActivity().findViewById(R.id.newsDescription) }
    private val newsPublishedAt: TextView by lazy { requireActivity().findViewById(R.id.newsPublishedAt) }
    private val newsUrl: TextView by lazy { requireActivity().findViewById(R.id.newsUrl) }
    private val newsToolbarImage: AppCompatImageView by lazy { requireActivity().findViewById(R.id.newsToolbarImage) }
    private val collapsingToolbar: CollapsingToolbarLayout by lazy {
        requireActivity().findViewById(
            R.id.collapsingToolbar
        )
    }
    private val newsAppBar: AppBarLayout by lazy { requireActivity().findViewById(R.id.newsAppBar) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)

    }

    private fun render(viewState: ViewState) {

        collapsingToolbar.title = bundle.getString("title")
        Glide
            .with(requireActivity())
            .load(bundle.getString("urlToImage"))
            .transform(RoundedCorners(30))
            .into(newsToolbarImage)
        newsDescription.text = bundle.getString("description")
        newsPublishedAt.text = bundle.getString("publishedAt")
        newsUrl.text = HtmlCompat.fromHtml(
            "<a href=${bundle.getString("url")}>Источник</a>",
            FROM_HTML_MODE_LEGACY
        )

    }

}