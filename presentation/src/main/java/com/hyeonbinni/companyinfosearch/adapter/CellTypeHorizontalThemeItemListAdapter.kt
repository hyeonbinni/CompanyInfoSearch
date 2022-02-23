package com.hyeonbinni.companyinfosearch

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hyeonbinni.companyinfosearch.databinding.ItemCellTypeHorizontalThemeUnitBinding
import com.hyeonbinni.domain.entity.CellTypeHorizontalThemeItem

class CellTypeHorizontalThemeItemListAdapter : ListAdapter<CellTypeHorizontalThemeItem.Theme, CellTypeHorizontalThemeItemListAdapter.CellTypeHorizontalThemeUnitListViewHolder>(CellTypeHorizontalThemeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellTypeHorizontalThemeUnitListViewHolder {
        return CellTypeHorizontalThemeUnitListViewHolder(
            ItemCellTypeHorizontalThemeUnitBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CellTypeHorizontalThemeUnitListViewHolder, position: Int) {
        getItem(position)?.run {
            holder.bind(this)
        }
    }

    class CellTypeHorizontalThemeUnitListViewHolder(private val binding: ItemCellTypeHorizontalThemeUnitBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item: CellTypeHorizontalThemeItem.Theme) {
                with(binding) {
                    clCoverImage.setBackgroundColor(Color.parseColor(item.color))

                    Glide.with(root)
                        .load(item.coverImage)
                        .into(ivCoverImage)

                    tvTitle.text = item.title
                }
            }
    }
}

private class CellTypeHorizontalThemeDiffCallback : DiffUtil.ItemCallback<CellTypeHorizontalThemeItem.Theme>() {
    override fun areItemsTheSame(oldItem: CellTypeHorizontalThemeItem.Theme, newItem: CellTypeHorizontalThemeItem.Theme): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CellTypeHorizontalThemeItem.Theme, newItem: CellTypeHorizontalThemeItem.Theme): Boolean {
        return oldItem == newItem
    }
}