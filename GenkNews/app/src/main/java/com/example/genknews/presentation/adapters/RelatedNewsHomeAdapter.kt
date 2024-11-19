package com.example.genknews.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.genknews.common.entity.NewsHomeRelation
import com.example.genknews.databinding.ItemNewsRelationBinding

class RelatedNewsHomeAdapter : RecyclerView.Adapter<RelatedNewsHomeAdapter.ViewHolder>() {

    /* **********************************************************************
     * DiffUtil
     ********************************************************************** */
    private val differCallback = object : DiffUtil.ItemCallback<NewsHomeRelation>() {
        override fun areItemsTheSame(
            oldItem: NewsHomeRelation,
            newItem: NewsHomeRelation
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: NewsHomeRelation,
            newItem: NewsHomeRelation
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<NewsHomeRelation>) {
        differ.submitList(list)
    }

    /* **********************************************************************
     * RecyclerView.Adapter Implementation
     ********************************************************************** */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemNewsRelationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        Log.i("", "Set fixed width for horizontal scrolling")
        val displayMetrics = parent.context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels

        Log.i("", "Set item width to 80% of screen width")
        binding.root.layoutParams.width = (screenWidth * 0.4).toInt()

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsRelationDB = differ.currentList[position]

        with(holder.binding) {
            Glide.with(holder.itemView)
                .load(newsRelationDB.avatar)
                .into(articleImage)

            articleDescription.text = newsRelationDB.sapo

            root.setOnClickListener {
                onItemClickListener?.invoke(newsRelationDB)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    /* **********************************************************************
     * Click Listener
     ********************************************************************** */
    private var onItemClickListener: ((NewsHomeRelation) -> Unit)? = null

    fun setOnItemClickListener(listener: (NewsHomeRelation) -> Unit) {
        onItemClickListener = listener
    }

    /* **********************************************************************
     * ViewHolder
     ********************************************************************** */
    inner class ViewHolder(val binding: ItemNewsRelationBinding) :
        RecyclerView.ViewHolder(binding.root)
}