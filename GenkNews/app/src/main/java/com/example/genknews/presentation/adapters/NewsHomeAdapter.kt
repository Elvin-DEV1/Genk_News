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
import com.example.genknews.common.entity.NewsHome
import com.example.genknews.common.entity.NewsHomeRelation
import com.example.genknews.databinding.ItemHeadNewsBinding
import com.example.genknews.databinding.ItemNewsHomeBinding
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class NewsHomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HEAD = 0
        private const val VIEW_TYPE_NORMAL = 1
    }

    private val differCallback =
        object : DiffUtil.ItemCallback<NewsHome>() {
            override fun areItemsTheSame(
                oldItem: NewsHome,
                newItem: NewsHome
            ): Boolean {
            return oldItem.url == newItem.url
        }

            override fun areContentsTheSame(
                oldItem: NewsHome,
                newItem: NewsHome
            ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_HEAD else VIEW_TYPE_NORMAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEAD -> {
                val binding = ItemHeadNewsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeadNewsViewHolder(binding)
            }
            else -> {
                val binding = ItemNewsHomeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                NormalNewsViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position in 0 until differ.currentList.size) {
            val newsItem = differ.currentList[position]

            when (holder) {
                is HeadNewsViewHolder -> holder.bind(newsItem)
                is NormalNewsViewHolder -> holder.bind(newsItem)
            }
        }
    }

    fun submitList(list: List<NewsHome>) {
        differ.submitList(list)
    }

    inner class HeadNewsViewHolder(private val binding: ItemHeadNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(news: NewsHome) {
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

                if (news.newsHomeRelation.isNotEmpty()) {
                    val relatedAdapter = RelatedNewsHomeAdapter()
                    recyclerNewsRelation.apply {
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = relatedAdapter
                    }
                    relatedAdapter.submitList(news.newsHomeRelation)

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

    inner class NormalNewsViewHolder(private val binding: ItemNewsHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(news: NewsHome) {
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

                if (news.newsHomeRelation.isNotEmpty()) {
                    val relatedAdapter = RelatedNewsHomeAdapter()
                    recyclerNewsRelation.apply {
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = relatedAdapter
                    }
                    relatedAdapter.submitList(news.newsHomeRelation)

                    relatedAdapter.setOnItemClickListener { relatedNews ->
                        onRelatedNewsClickListener?.invoke(relatedNews)
                    }
                } else {
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

    private var onItemClickListener: ((NewsHome) -> Unit)? = null
    private var onRelatedNewsClickListener: ((NewsHomeRelation) -> Unit)? = null

    fun setOnItemClickListener(listener: (NewsHome) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnRelatedNewsClickListener(listener: (NewsHomeRelation) -> Unit) {
        onRelatedNewsClickListener = listener
    }
}