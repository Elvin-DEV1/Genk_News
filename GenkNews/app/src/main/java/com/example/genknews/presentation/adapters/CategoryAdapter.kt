package com.example.genknews.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.genknews.control.entity.CategoryDB
import com.example.genknews.databinding.ItemCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    /* **********************************************************************
     * DiffUtil
     ********************************************************************** */
    private val differCallback = object : DiffUtil.ItemCallback<CategoryDB>() {
        override fun areItemsTheSame(
            oldItem: CategoryDB,
            newItem: CategoryDB
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CategoryDB,
            newItem: CategoryDB
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<CategoryDB>) {
        differ.submitList(list)
    }

    /* **********************************************************************
     * RecyclerView.Adapter Implementation
     ********************************************************************** */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        // Set width to 50% of parent width for 2 items per row
        val displayMetrics = parent.context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        binding.root.layoutParams.width = screenWidth / 2

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = differ.currentList[position]

        with(holder.binding) {
            imgZone.setImageResource(category.logo.toInt())
            txtZone.text = category.name

            root.setOnClickListener {
                onItemClickListener?.invoke(category)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    /* **********************************************************************
     * Click Listener
     ********************************************************************** */
    private var onItemClickListener: ((CategoryDB) -> Unit)? = null

    fun setOnItemClickListener(listener: (CategoryDB) -> Unit) {
        onItemClickListener = listener
    }

    /* **********************************************************************
     * ViewHolder
     ********************************************************************** */
    inner class ViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}