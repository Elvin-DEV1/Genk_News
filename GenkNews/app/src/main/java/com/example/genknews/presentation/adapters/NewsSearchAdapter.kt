package com.example.genknews.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.genknews.control.entity.NewsSearchDB
import com.example.genknews.databinding.ItemNewsSearchBinding

class NewsSearchAdapter : RecyclerView.Adapter<NewsSearchAdapter.ViewHolder>() {

    /* **
     * DiffUtil
     ** */
    private val differCallback = object : DiffUtil.ItemCallback<NewsSearchDB>() {
        override fun areItemsTheSame(oldItem: NewsSearchDB, newItem: NewsSearchDB): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: NewsSearchDB, newItem: NewsSearchDB): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    /* **
     * RecyclerView.Adapter Implementation
     ** */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsSearchDB = differ.currentList[position]

        with(holder.binding) {
            Glide.with(holder.itemView)
                .load(newsSearchDB.avatar)
                .into(articleImage)

            articleTitle.text = newsSearchDB.title
            articleDescription.text = newsSearchDB.sapo
            articleCategory.text = newsSearchDB.zoneName
            articleDateTime.text = newsSearchDB.distributionDate

            root.setOnClickListener {
                onItemClickListener?.invoke(newsSearchDB)
            }
        }
    }

    /* **
     * Click Listener
     ** */
    private var onItemClickListener: ((NewsSearchDB) -> Unit)? = null

    fun setOnItemClickListener(listener: (NewsSearchDB) -> Unit) {
        onItemClickListener = listener
    }

    /* **
     * ViewHolder
     ** */
    inner class ViewHolder(val binding: ItemNewsSearchBinding) :
        RecyclerView.ViewHolder(binding.root)
}