package com.example.genknews.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.genknews.common.entity.Category
import com.example.genknews.databinding.ItemCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<Category>) {
        differ.submitList(list)
    }

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            with(binding) {
                Glide.with(itemView)
                    .load(category.logo)
                    .into(imgZone)

                txtZone.text = category.name

                zone.setOnClickListener {
                    onItemClickListener?.invoke(category)
                }
            }
        }
    }

    private var onItemClickListener: ((Category) -> Unit)? = null

    fun setOnItemClickListener(listener: (Category) -> Unit) {
        onItemClickListener = listener
    }
}