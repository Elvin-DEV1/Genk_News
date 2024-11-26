package com.example.genknews.presentation.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.genknews.R
import com.example.genknews.common.entity.NewsLatest
import com.example.genknews.common.entity.NewsLatestRelation
import com.example.genknews.databinding.ItemNewsHomeBinding
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class NewsLatestAdapter : RecyclerView.Adapter<NewsLatestAdapter.NewsViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<NewsLatest>() {
        override fun areItemsTheSame(
            oldItem: NewsLatest,
            newItem: NewsLatest
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: NewsLatest,
            newItem: NewsLatest
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = differ.currentList[position]
        holder.bind(newsItem)
    }

    fun submitList(list: List<NewsLatest>) {
        differ.submitList(list)
    }

    inner class NewsViewHolder(private val binding: ItemNewsHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(news: NewsLatest) {
            with(binding) {
                Glide.with(itemView)
                    .load(news.avatar)
                    .into(articleImage)

                articleTitle.text = news.title
                articleDescription.text = news.sapo
                articleCategory.text = news.zoneName
                articleDateTime.text = formatTimeFromISO(news.distributionDate)
                articleSource.text = news.source

                root.setOnClickListener {
                    onItemClickListener?.invoke(news)
                }

                articleRelation.setOnClickListener {
                    if (NewsRelation.visibility == View.VISIBLE) {
                        closeImage.visibility = View.INVISIBLE
                        addImage.visibility = View.VISIBLE
                        articleRelation.setTextColor(
                            ContextCompat.getColor(
                                itemView.context,
                                R.color.colorTextCategory
                            )
                        )
                        NewsRelation.visibility = View.GONE
                    } else {
                        closeImage.visibility = View.VISIBLE
                        addImage.visibility = View.INVISIBLE
                        articleRelation.setTextColor(
                            ContextCompat.getColor(
                                itemView.context,
                                R.color.colorNewsRelation
                            )
                        )
                        NewsRelation.visibility = View.VISIBLE
                    }
                }

                if (news.newsRelation.isNotEmpty()) {
                    val relatedAdapter = RelatedNewsLatestAdapter()
                    recyclerNewsRelation.apply {
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = relatedAdapter
                    }
                    relatedAdapter.submitList(news.newsRelation)

                    relatedAdapter.setOnItemClickListener { relatedNews ->
                        onRelatedNewsClickListener?.invoke(relatedNews)
                    }
                }else{
                    txtNewsRelation.visibility = View.GONE
                    recyclerNewsRelation.visibility = View.GONE
                    closeImage.visibility = View.INVISIBLE
                    addImage.visibility = View.VISIBLE
                    articleRelation.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.colorTextCategory
                        )
                    )
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

    private var onItemClickListener: ((NewsLatest) -> Unit)? = null
    private var onRelatedNewsClickListener: ((NewsLatestRelation) -> Unit)? = null

    fun setOnItemClickListener(listener: (NewsLatest) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnRelatedNewsClickListener(listener: (NewsLatestRelation) -> Unit) {
        onRelatedNewsClickListener = listener
    }
}