package com.hyeonbinni.companyinfosearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyeonbinni.companyinfosearch.databinding.ItemCellTypeCompanyBinding
import com.hyeonbinni.companyinfosearch.databinding.ItemCellTypeHorizontalThemeBinding
import com.hyeonbinni.companyinfosearch.databinding.ItemCellTypeReviewBinding
import com.hyeonbinni.companyinfosearch.ui.view.SearchCompanyListFragmentDirections
import com.hyeonbinni.domain.*
import com.hyeonbinni.domain.entity.CellTypeCompanyItem
import com.hyeonbinni.domain.entity.CellTypeHorizontalThemeItem
import com.hyeonbinni.domain.entity.CellTypeItem
import com.hyeonbinni.domain.entity.CellTypeReviewItem

class CompanySearchItemListAdapter : PagingDataAdapter<CellTypeItem.Item, RecyclerView.ViewHolder>(CompanySearchItemListDiffCallback()) {
    private val VIEW_TYPE_COMPANY = 0
    private val VIEW_TYPE_REVIEW = 1
    private val VIEW_TYPE_HORIZONTAL_THEME = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_COMPANY -> {
                CellTypeCompanyListViewHolder(
                    ItemCellTypeCompanyBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_REVIEW -> {
                CellTypeReviewListViewHolder(
                    ItemCellTypeReviewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                CellTypeHorizontalThemeListViewHolder(
                    ItemCellTypeHorizontalThemeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.run {
            when(holder) {
                is CellTypeCompanyListViewHolder -> {
                    holder.bind(this as CellTypeCompanyItem)
                }
                is CellTypeReviewListViewHolder -> {
                    holder.bind(this as CellTypeReviewItem)
                }
                is CellTypeHorizontalThemeListViewHolder -> {
                    holder.bind(this as CellTypeHorizontalThemeItem)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        getItem(position)?.run {
            return when(cellType) {
                CellType.CELL_TYPE_COMPANY -> {
                    VIEW_TYPE_COMPANY
                }
                CellType.CELL_TYPE_REVIEW -> {
                    VIEW_TYPE_REVIEW
                }
                else -> {
                    VIEW_TYPE_HORIZONTAL_THEME
                }
            }
        }

        return VIEW_TYPE_HORIZONTAL_THEME
    }

    class CellTypeCompanyListViewHolder(private val binding: ItemCellTypeCompanyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CellTypeCompanyItem) {
            with(binding) {
                this.item = item
                this.holder = this@CellTypeCompanyListViewHolder

                executePendingBindings()
            }
        }

        fun navigateToDetail(item: CellTypeCompanyItem, view: View) {
            val direction =
                SearchCompanyListFragmentDirections.actionSearchCompanyListFragmentToSearchCompanyDetailFragment(
                    companyId = item.companyId,
                    logoPath = item.logoPath,
                    companyName = item.name,
                    rateTotalAvg = item.rateTotalAvg,
                    industryName = item.industryName
                )

            view.findNavController().navigate(direction)
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

    class CellTypeHorizontalThemeListViewHolder(private val binding: ItemCellTypeHorizontalThemeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CellTypeHorizontalThemeItem) {
            with(binding) {
                with(rvCellTypeHorizontalTheme) {
                    setHasFixedSize(true)

                    layoutManager = LinearLayoutManager(context).apply {
                        orientation = LinearLayoutManager.HORIZONTAL
                    }

                    adapter = CellTypeHorizontalThemeItemListAdapter().apply {
                        submitList(item.themes)
                    }
                }

                executePendingBindings()
            }
        }
    }
}

private class CompanySearchItemListDiffCallback : DiffUtil.ItemCallback<CellTypeItem.Item>() {
    override fun areItemsTheSame(oldItem: CellTypeItem.Item, newItem: CellTypeItem.Item): Boolean {
        return areCellTypeItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: CellTypeItem.Item, newItem: CellTypeItem.Item): Boolean {
        return oldItem == newItem
    }

    private fun areCellTypeItemsTheSame(oldItem: CellTypeItem.Item, newItem: CellTypeItem.Item): Boolean {
        if(oldItem.cellType != newItem.cellType) return false

        return when {
            (oldItem is CellTypeCompanyItem) && (newItem is CellTypeCompanyItem) -> {
                oldItem.companyId == newItem.companyId
            }
            (oldItem is CellTypeReviewItem) && (newItem is CellTypeReviewItem) -> {
                oldItem.companyId == newItem.companyId
            }
            else -> {
                false
            }
        }
    }
}