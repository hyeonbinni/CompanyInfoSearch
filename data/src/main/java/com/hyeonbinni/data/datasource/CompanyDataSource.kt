package com.hyeonbinni.data.datasource

import androidx.paging.PagingData
import com.hyeonbinni.domain.entity.CellTypeItem
import com.hyeonbinni.domain.entity.CellTypeReviewItem
import io.reactivex.rxjava3.core.Flowable

interface CompanyDataSource {

    fun getCompanySearch(query: String): Flowable<PagingData<CellTypeItem.Item>>

    fun getCompanyReview(companyId: Int): Flowable<PagingData<CellTypeReviewItem>>

}