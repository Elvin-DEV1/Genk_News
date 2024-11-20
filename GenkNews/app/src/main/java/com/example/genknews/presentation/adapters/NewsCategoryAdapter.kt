package com.example.genknews.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.genknews.common.entity.NewsCategory
import com.example.genknews.common.entity.NewsCategoryRelation
import com.example.genknews.common.entity.NewsLatest
import com.example.genknews.common.entity.NewsLatestRelation
import com.example.genknews.databinding.ItemNewsHomeBinding

class NewsCategoryAdapter : RecyclerView.Adapter<NewsCategoryAdapter.NewsViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<NewsCategory>() {
        override fun areItemsTheSame(
            oldItem: NewsCategory,
            newItem: NewsCategory
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: NewsCategory,
            newItem: NewsCategory
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

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = differ.currentList[position]
        holder.bind(newsItem)
    }

    fun submitList(list: List<NewsCategory>) {
        differ.submitList(list)
    }

    inner class NewsViewHolder(private val binding: ItemNewsHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: NewsCategory) {
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

                if (news.newsRelation.isNotEmpty()) {
                    val relatedAdapter = RelatedNewsCategoryAdapter()
                    recyclerNewsRelation.apply {
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = relatedAdapter
                    }
                    relatedAdapter.submitList(news.newsRelation)

                    relatedAdapter.setOnItemClickListener { relatedNews ->
                        onRelatedNewsClickListener?.invoke(relatedNews)
                    }
                } else {
                    articleRelation.visibility = View.GONE
                }
            }
        }
    }

    private var onItemClickListener: ((NewsCategory) -> Unit)? = null
    private var onRelatedNewsClickListener: ((NewsCategoryRelation) -> Unit)? = null

    fun setOnItemClickListener(listener: (NewsCategory) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnRelatedNewsClickListener(listener: (NewsCategoryRelation) -> Unit) {
        onRelatedNewsClickListener = listener
    }
}