package com.hyeonbinni.companyinfosearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hyeonbinni.companyinfosearch.databinding.ItemCellTypeReviewBinding
import com.hyeonbinni.domain.entity.CellTypeReviewItem

class CellTypeReviewItemListAdapter : PagingDataAdapter<CellTypeReviewItem, CellTypeReviewItemListAdapter.CellTypeReviewListViewHolder>(CellTypeReviewListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellTypeReviewListViewHolder {
        return CellTypeReviewListViewHolder(
            ItemCellTypeReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CellTypeReviewListViewHolder, position: Int) {
        getItem(position)?.run {
            holder.bind(this)
        }
    }

    class CellTypeReviewListViewHolder(private val binding: ItemCellTypeReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CellTypeReviewItem) {
            with(binding) {
                this.item = item

                executePendingBindings()
            }
        }
    }
}

private class CellTypeReviewListDiffCallback : DiffUtil.ItemCallback<CellTypeReviewItem>() {
    override fun areItemsTheSame(oldItem: CellTypeReviewItem, newItem: CellTypeReviewItem): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: CellTypeReviewItem, newItem: CellTypeReviewItem): Boolean {
        return oldItem == newItem
    }
}