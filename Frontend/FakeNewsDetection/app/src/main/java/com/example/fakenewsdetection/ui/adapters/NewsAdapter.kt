package com.example.fakenewsdetection.ui.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakenewsdetection.R
import com.example.fakenewsdetection.data.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var articles = listOf<Article>()

    fun submitList(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivNews: ImageView = itemView.findViewById(R.id.ivNews)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private val tvSource: TextView = itemView.findViewById(R.id.tvSource)
        private val tvDate: TextView = itemView.findViewById(R.id.tvDate)

        fun bind(article: Article) {

            tvTitle.text = article.title
            tvDescription.text = article.description ?: "No description available"
            tvSource.text = article.source.name
            tvDate.text = article.publishedAt.take(10)

            Glide.with(itemView.context)
                .load(article.image
)
                .placeholder(R.drawable.ic_news_placeholder)
                .into(ivNews)

            itemView.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(article.url)
                )
                itemView.context.startActivity(intent)
            }
        }
    }
}
