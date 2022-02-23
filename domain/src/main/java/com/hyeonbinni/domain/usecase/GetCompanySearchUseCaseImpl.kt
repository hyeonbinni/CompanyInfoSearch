package com.hyeonbinni.domain.usecase

import androidx.paging.PagingData
import com.hyeonbinni.domain.entity.CellTypeItem
import com.hyeonbinni.domain.repository.CompanyRepository
import io.reactivex.rxjava3.core.Flowable

class GetCompanySearchUseCaseImpl(
    private val companyRepository: CompanyRepository
) : GetCompanySearchUseCase {

    override fun invoke(query: String): Flowable<PagingData<CellTypeItem.Item>> {
        return companyRepository.getCompanySearch(query)
    }

}