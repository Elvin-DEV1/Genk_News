package com.example.genknews.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.genknews.R
import com.example.genknews.control.entity.NewsLatestDB
import com.example.genknews.databinding.ItemNewsHomeBinding

class NewsLatestAdapter : RecyclerView.Adapter<NewsLatestAdapter.NewsLatestViewHolder>() {
    inner class NewsLatestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    lateinit var newsImage: ImageView
    lateinit var newsTitle: TextView
    lateinit var newsDescription: TextView
    lateinit var newsCategory: TextView
    lateinit var newsDatetime: TextView
    lateinit var newsSource: TextView

    private val differCallback = object : DiffUtil.ItemCallback<NewsLatestDB>(){
        override fun areItemsTheSame(oldItem: NewsLatestDB, newItem: NewsLatestDB): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: NewsLatestDB, newItem: NewsLatestDB): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsLatestViewHolder {
        return NewsLatestViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news_home, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((NewsLatestDB) -> Unit)? = null

    override fun onBindViewHolder(holder: NewsLatestViewHolder, position: Int) {
        val newsLatestDB = differ.currentList[position]

        newsImage = holder.itemView.findViewById(R.id.articleImage)
        newsTitle = holder.itemView.findViewById(R.id.articleTitle)
        newsDescription = holder.itemView.findViewById(R.id.articleDescription)
        newsCategory = holder.itemView.findViewById(R.id.articleCategory)
        newsDatetime = holder.itemView.findViewById(R.id.articleDateTime)
        newsSource = holder.itemView.findViewById(R.id.articleSource)

        holder.itemView.apply {
            Glide.with(this).load(newsLatestDB.avatar).into(newsImage)
            newsTitle.text = newsLatestDB.title
            newsDescription.text = newsLatestDB.sapo
            newsCategory.text = newsLatestDB.zoneName
            newsDatetime.text = newsLatestDB.distributionDate
            newsSource.text = newsLatestDB.source

            setOnClickListener {
                onItemClickListener?.let {
                    it(newsLatestDB)
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (NewsLatestDB) -> Unit){
        onItemClickListener = listener
    }

    inner class ViewHolder(binding: ItemNewsHomeBinding) :
        RecyclerView.ViewHolder(binding.root)
}