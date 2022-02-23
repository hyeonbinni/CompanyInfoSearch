package com.hyeonbinni.data

import androidx.paging.PagingData
import com.hyeonbinni.data.datasource.CompanyDataSource
import com.hyeonbinni.domain.entity.CellTypeItem
import com.hyeonbinni.domain.entity.CellTypeReviewItem
import com.hyeonbinni.domain.repository.CompanyRepository
import io.reactivex.rxjava3.core.Flowable

class CompanyRepositoryImpl(
    private val dataSource: CompanyDataSource
) : CompanyRepository {

    override fun getCompanySearch(query: String): Flowable<PagingData<CellTypeItem.Item>> {
        return dataSource.getCompanySearch(query)
    }

    override fun getCompanyReview(companyId: Int): Flowable<PagingData<CellTypeReviewItem>> {
        return dataSource.getCompanyReview(companyId)
    }

}