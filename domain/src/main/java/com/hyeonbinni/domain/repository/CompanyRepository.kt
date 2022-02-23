package com.hyeonbinni.domain.repository

import androidx.paging.PagingData
import com.hyeonbinni.domain.entity.CellTypeItem
import com.hyeonbinni.domain.entity.CellTypeReviewItem
import io.reactivex.rxjava3.core.Flowable

interface CompanyRepository {

    fun getCompanySearch(query: String): Flowable<PagingData<CellTypeItem.Item>>

    fun getCompanyReview(companyId: Int): Flowable<PagingData<CellTypeReviewItem>>

}