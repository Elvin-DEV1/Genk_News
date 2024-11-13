package com.example.genknews.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.genknews.common.logger.Logger
import com.example.genknews.control.entity.NewsRelation
import com.example.genknews.databinding.ItemNewsRelationBinding

class RelatedNewsAdapter : RecyclerView.Adapter<RelatedNewsAdapter.ViewHolder>() {

    /* **********************************************************************
     * DiffUtil
     ********************************************************************** */
    private val differCallback = object : DiffUtil.ItemCallback<NewsRelation>() {
        override fun areItemsTheSame(
            oldItem: NewsRelation,
            newItem: NewsRelation
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: NewsRelation,
            newItem: NewsRelation
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    // Function to submit list
    fun submitList(list: List<NewsRelation>) {
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
        binding.root.layoutParams.width = (screenWidth * 0.8).toInt()

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
    private var onItemClickListener: ((NewsRelation) -> Unit)? = null

    fun setOnItemClickListener(listener: (NewsRelation) -> Unit) {
        onItemClickListener = listener
    }

    /* **********************************************************************
     * ViewHolder
     ********************************************************************** */
    inner class ViewHolder(val binding: ItemNewsRelationBinding) :
        RecyclerView.ViewHolder(binding.root)
}