package com.example.genknews.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.genknews.control.entity.NewsLatestDB
import com.example.genknews.control.entity.NewsRelation
import com.example.genknews.databinding.ItemNewsHomeBinding

class NewsLatestAdapter : RecyclerView.Adapter<NewsLatestAdapter.ViewHolder>() {

    /* **********************************************************************
     * DiffUtil
     ********************************************************************** */
    private val differCallback = object : DiffUtil.ItemCallback<NewsLatestDB>() {
        override fun areItemsTheSame(oldItem: NewsLatestDB, newItem: NewsLatestDB): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: NewsLatestDB, newItem: NewsLatestDB): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<NewsLatestDB>) {
        differ.submitList(list)
    }

    /* **********************************************************************
     * RecyclerView.Adapter Implementation
     ********************************************************************** */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsLatestDB = differ.currentList[position]

        with(holder.binding) {
            Glide.with(holder.itemView)
                .load(newsLatestDB.avatar)
                .into(articleImage)

            articleTitle.text = newsLatestDB.title
            articleDescription.text = newsLatestDB.sapo
            articleCategory.text = newsLatestDB.zoneName
            articleDateTime.text = newsLatestDB.distributionDate
            articleSource.text = newsLatestDB.source

            root.setOnClickListener {
                onItemClickListener?.invoke(newsLatestDB)
            }

            // Tin liên quan button click
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

            // Thiết lập RecyclerView tin liên quan nếu có
            if (newsLatestDB.newsRelation.isNotEmpty()) {
                val relatedAdapter = RelatedNewsAdapter()
                recyclerNewsRelation.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = relatedAdapter
                }
                relatedAdapter.submitList(newsLatestDB.newsRelation)

                // Optional: Set click listener for related news items
                relatedAdapter.setOnItemClickListener { relatedNews ->
                    onRelatedNewsClickListener?.invoke(relatedNews)
                }
            } else {
                // Hide the related news section if there are no related news
                articleRelation.visibility = View.GONE
            }
        }
    }

    /* **********************************************************************
     * Click Listeners
     ********************************************************************** */
    private var onItemClickListener: ((NewsLatestDB) -> Unit)? = null
    private var onRelatedNewsClickListener: ((NewsRelation) -> Unit)? = null

    fun setOnItemClickListener(listener: (NewsLatestDB) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnRelatedNewsClickListener(listener: (NewsRelation) -> Unit) {
        onRelatedNewsClickListener = listener
    }

    /* **********************************************************************
     * ViewHolder
     ********************************************************************** */
    inner class ViewHolder(val binding: ItemNewsHomeBinding) :
        RecyclerView.ViewHolder(binding.root)
}