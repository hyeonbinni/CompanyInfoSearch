package com.hyeonbinni.data

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.hyeonbinni.data.model.toCellTypeItem
import com.hyeonbinni.data.network.ApiService
import com.hyeonbinni.domain.entity.CellTypeReviewItem
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class CompanyReviewPagingSource(
    private val apiService: ApiService,
    private val companyId: Int
) : RxPagingSource<Int, CellTypeReviewItem>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, CellTypeReviewItem>> {
        val position = params.key ?: 1

        return apiService.getCompanyReview(companyId, position)
            .subscribeOn(Schedulers.io())
            .map { res -> res.toCellTypeItem() }
            .map<LoadResult<Int, CellTypeReviewItem>?> { cellTypeItem ->
                LoadResult.Page(
                    data = cellTypeItem.items.map { item ->
                      item as CellTypeReviewItem
                    },
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (position == cellTypeItem.totalPage) null else position + 1
                )
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    override fun getRefreshKey(state: PagingState<Int, CellTypeReviewItem>): Int? {
        return state.anchorPosition?.run {
            state.closestPageToPosition(this)?.prevKey
        }
    }
}