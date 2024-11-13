package com.example.genknews.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.genknews.control.entity.NewsHomeDB
import com.example.genknews.databinding.ItemHeadNewsBinding
import com.example.genknews.databinding.ItemNewsHomeBinding

class NewsHomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HEAD = 0  // Item đầu tiên
        private const val VIEW_TYPE_NORMAL = 1 // Các item còn lại
    }

    private val differCallback = object : DiffUtil.ItemCallback<NewsHomeDB>() {
        override fun areItemsTheSame(oldItem: NewsHomeDB, newItem: NewsHomeDB): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: NewsHomeDB, newItem: NewsHomeDB): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val newsItem = differ.currentList[position]

        when (holder) {
            is HeadNewsViewHolder -> holder.bind(newsItem)
            is NormalNewsViewHolder -> holder.bind(newsItem)
        }
    }

    fun submitList(list: List<NewsHomeDB>) {
        differ.submitList(list)
    }

    inner class HeadNewsViewHolder(private val binding: ItemHeadNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: NewsHomeDB) {
            with(binding) {
                Glide.with(itemView)
                    .load(news.avatar)
                    .into(articleImage)

                articleTitle.text = news.title
                articleDescription.text = news.sapo
                articleCategory.text = news.zoneName
                articleDateTime.text = news.distributionDate
                articleSource.text = news.source

                root.setOnClickListener {
                    onItemClickListener?.invoke(news)
                }

                // Tin liên quan
                articleRelation.setOnClickListener {
                    NewsRelation.visibility = if (NewsRelation.visibility == View.VISIBLE) {
                        View.GONE
                    } else {
                        View.VISIBLE
                    }
                }

                // Thiết lập RecyclerView tin liên quan nếu cần
                if (news.newsRelation.isNotEmpty()) {
                    val relatedAdapter = RelatedNewsAdapter() // Tạo adapter riêng cho tin liên quan
                    recyclerNewsRelation.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = relatedAdapter
                    }
                    relatedAdapter.submitList(news.newsRelation)
                }
            }
        }
    }

    inner class NormalNewsViewHolder(private val binding: ItemNewsHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: NewsHomeDB) {
            with(binding) {
                Glide.with(itemView)
                    .load(news.avatar)
                    .into(articleImage)

                articleTitle.text = news.title
                articleDescription.text = news.sapo
                articleCategory.text = news.zoneName
                articleDateTime.text = news.distributionDate
                articleSource.text = news.source

                root.setOnClickListener {
                    onItemClickListener?.invoke(news)
                }

                // Tin liên quan
                articleRelation.setOnClickListener {
                    NewsRelation.visibility = if (NewsRelation.visibility == View.VISIBLE) {
                        closeImage.visibility = View.INVISIBLE
                        addImage.visibility = View.VISIBLE
                        View.GONE
                    } else {
                        closeImage.visibility = View.VISIBLE
                        addImage.visibility = View.INVISIBLE
                        View.VISIBLE
                    }
                }

                // Thiết lập RecyclerView tin liên quan nếu cần
                if (news.newsRelation.isNotEmpty()) {
                    val relatedAdapter = RelatedNewsAdapter()
                    recyclerNewsRelation.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = relatedAdapter
                    }
                    relatedAdapter.submitList(news.newsRelation)
                }
            }
        }
    }

    private var onItemClickListener: ((NewsHomeDB) -> Unit)? = null

    fun setOnItemClickListener(listener: (NewsHomeDB) -> Unit) {
        onItemClickListener = listener
    }
}