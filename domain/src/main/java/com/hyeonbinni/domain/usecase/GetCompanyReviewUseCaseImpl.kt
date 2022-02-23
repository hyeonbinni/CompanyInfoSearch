package com.hyeonbinni.domain.usecase

import androidx.paging.PagingData
import com.hyeonbinni.domain.entity.CellTypeReviewItem
import com.hyeonbinni.domain.repository.CompanyRepository
import io.reactivex.rxjava3.core.Flowable

class GetCompanyReviewUseCaseImpl(
    private val companyRepository: CompanyRepository
) : GetCompanyReviewUseCase {

    override fun invoke(companyId: Int): Flowable<PagingData<CellTypeReviewItem>> {
        return companyRepository.getCompanyReview(companyId)
    }

}