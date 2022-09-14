package com.example.newsfetcher.feature.bookmark.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel

class BookmarksAdapter() : RecyclerView.Adapter<BookmarksAdapter.ViewHolder>() {

    private var articlesData: List<ArticleModel> = emptyList()

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val tvPublishedAt: TextView
        val ivBookmark: ImageView
        val ivUrlToImage: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            tvTitle = view.findViewById(R.id.tvTitle)
            tvPublishedAt = view.findViewById(R.id.tvPublishedAt)
            ivBookmark = view.findViewById(R.id.ivBookmark)
            ivUrlToImage = view.findViewById(R.id.ivUrlToImage)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvTitle.text = articlesData[position].title
        viewHolder.tvPublishedAt.text = articlesData[position].publishedAt
        Glide
            .with(viewHolder.itemView)
            .load(articlesData[position].urlToImage)
            .transform(RoundedCorners(30))
            .into(viewHolder.ivUrlToImage)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = articlesData.size

    fun setData(articles: List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()
    }
}