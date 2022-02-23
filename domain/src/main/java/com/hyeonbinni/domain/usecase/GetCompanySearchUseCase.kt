package com.hyeonbinni.domain.usecase

import androidx.paging.PagingData
import com.hyeonbinni.domain.entity.CellTypeItem
import io.reactivex.rxjava3.core.Flowable

interface GetCompanySearchUseCase {
    fun invoke(query: String): Flowable<PagingData<CellTypeItem.Item>>
}