package com.loci.kakaoimagesearch.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.loci.kakaoimagesearch.data.remote.model.TotalEntity
import com.loci.kakaoimagesearch.databinding.ItemSearchBinding
import com.loci.kakaoimagesearch.presentation.main.SearchListViewAdapter.SearchListViewHolder
import com.loci.kakaoimagesearch.util.dateToStringFormat

class SearchListViewAdapter : ListAdapter<TotalEntity, SearchListViewHolder>(diffUtil) {

    class SearchListViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TotalEntity) {
            binding.apply {
                Glide.with(itemView.context).load(item.thumbnailUrl).into(ivSearchThumbnail)
                tvSearchTitle.text = item.title
                tvSearchDate.text = dateToStringFormat(item.datetime)
                ivIsLikedIcon.isVisible = item.isLiked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        return SearchListViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TotalEntity>() {
            override fun areItemsTheSame(
                oldItem: TotalEntity,
                newItem: TotalEntity
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: TotalEntity,
                newItem: TotalEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}