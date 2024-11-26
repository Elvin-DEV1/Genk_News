package com.example.genknews.presentation.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.genknews.common.entity.SearchNews
import com.example.genknews.databinding.ItemNewsSearchBinding
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class NewsSearchAdapter : RecyclerView.Adapter<NewsSearchAdapter.NewsSearchViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<SearchNews>() {
        override fun areItemsTheSame(oldItem: SearchNews, newItem: SearchNews): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: SearchNews, newItem: SearchNews): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSearchViewHolder {
        val binding = ItemNewsSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsSearchViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsSearchViewHolder, position: Int) {
        val newsItem = differ.currentList[position]
        holder.bind(newsItem)
    }

    fun submitList(list: List<SearchNews>) {
        differ.submitList(list)
    }

    inner class NewsSearchViewHolder(private val binding: ItemNewsSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(news: SearchNews) {
            with(binding) {
                Glide.with(itemView)
                    .load(news.avatar)
                    .into(articleImage)

                articleTitle.text = news.title
                articleDescription.text = news.sapo
                articleCategory.text = news.zoneName
                articleDateTime.text = formatTimeFromISO(news.distributionDate)

                root.setOnClickListener {
                    onItemClickListener?.invoke(news)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatTimeFromISO(dateTime: String): String {
        try {
            val parsedTime = ZonedDateTime.parse(dateTime, DateTimeFormatter.ISO_ZONED_DATE_TIME)
            val now = ZonedDateTime.now()

            val diffInMinutes = ChronoUnit.MINUTES.between(parsedTime, now)
            val diffInHours = ChronoUnit.HOURS.between(parsedTime, now)
            val diffInDays = ChronoUnit.DAYS.between(parsedTime, now)

            return when {
                diffInMinutes < 60 -> "$diffInMinutes phút trước"
                diffInHours < 24 -> "$diffInHours giờ trước"
                else -> "$diffInDays ngày trước"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return "Không xác định"
        }
    }

    private var onItemClickListener: ((SearchNews) -> Unit)? = null

    fun setOnItemClickListener(listener: (SearchNews) -> Unit) {
        onItemClickListener = listener
    }
}