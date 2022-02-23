package com.hyeonbinni.data

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.hyeonbinni.data.model.toCellTypeItem
import com.hyeonbinni.domain.entity.CellTypeItem
import com.hyeonbinni.data.network.ApiService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class CompanySearchPagingSource(
    private val apiService: ApiService,
    private val query: String
) : RxPagingSource<Int, CellTypeItem.Item>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, CellTypeItem.Item>> {
        val position = params.key ?: 1

        return apiService.getCompanySearch(query, position)
            .subscribeOn(Schedulers.io())
            .map { res -> res.toCellTypeItem() }
            .map<LoadResult<Int, CellTypeItem.Item>?> { cellTypeItem ->
                LoadResult.Page(
                    data = cellTypeItem.items,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (position == cellTypeItem.totalPage) null else position + 1
                )
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    override fun getRefreshKey(state: PagingState<Int, CellTypeItem.Item>): Int? {
        return state.anchorPosition?.run {
            state.closestPageToPosition(this)?.prevKey
        }
    }
}