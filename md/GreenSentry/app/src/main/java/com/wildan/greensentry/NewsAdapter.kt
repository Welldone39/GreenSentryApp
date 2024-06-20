package com.wildan.greensentry

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wildan.greensentry.model.News

class NewsAdapter(private var newsList: List<News>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.action_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.bind(news)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updateData(newNewsList: List<News>) {
        newsList = newNewsList
        notifyDataSetChanged()
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.action_title)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.action_description)
        private val imageView: ImageView = itemView.findViewById(R.id.action_image)

        fun bind(news: News) {
            titleTextView.text = news.title
            Log.d("Debug News", news.url_To_Image.toString())
            descriptionTextView.text = news.description
            Glide.with(itemView.context)
                .load(news.url_To_Image)
                .placeholder(R.drawable.ic_launcher_background) // Placeholder image
                .error(R.drawable.ic_launcher_background) // Error image
                .into(imageView)
        }
    }
}