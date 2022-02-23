package com.hyeonbinni.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.hyeonbinni.data.CompanyReviewPagingSource
import com.hyeonbinni.data.CompanySearchPagingSource
import com.hyeonbinni.data.network.ApiService
import com.hyeonbinni.domain.entity.CellTypeItem
import com.hyeonbinni.domain.entity.CellTypeReviewItem
import io.reactivex.rxjava3.core.Flowable

class CompanyDataSourceImpl(
    private val apiService: ApiService
) : CompanyDataSource {

    override fun getCompanySearch(query: String): Flowable<PagingData<CellTypeItem.Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CompanySearchPagingSource(apiService, query) }
        ).flowable
    }

    override fun getCompanyReview(companyId: Int): Flowable<PagingData<CellTypeReviewItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CompanyReviewPagingSource(apiService, companyId) }
        ).flowable
    }
}