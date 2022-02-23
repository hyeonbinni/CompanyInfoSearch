package com.hyeonbinni.domain.usecase

import androidx.paging.PagingData
import com.hyeonbinni.domain.entity.CellTypeReviewItem
import io.reactivex.rxjava3.core.Flowable

interface GetCompanyReviewUseCase {
    fun invoke(companyId: Int): Flowable<PagingData<CellTypeReviewItem>>
}