package com.hyeonbinni.companyinfosearch

import com.hyeonbinni.companyinfosearch.ui.viewmodel.SearchCompanyDetailViewModel
import com.hyeonbinni.companyinfosearch.ui.viewmodel.SearchCompanyListViewModel
import com.hyeonbinni.data.CompanyRepositoryImpl
import com.hyeonbinni.data.datasource.CompanyDataSource
import com.hyeonbinni.data.datasource.CompanyDataSourceImpl
import com.hyeonbinni.data.network.ApiService
import com.hyeonbinni.domain.repository.CompanyRepository
import com.hyeonbinni.domain.usecase.GetCompanyReviewUseCase
import com.hyeonbinni.domain.usecase.GetCompanyReviewUseCaseImpl
import com.hyeonbinni.domain.usecase.GetCompanySearchUseCase
import com.hyeonbinni.domain.usecase.GetCompanySearchUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single { ApiService.create() }

    single<CompanyDataSource> { CompanyDataSourceImpl(get()) }

    single<CompanyRepository> { CompanyRepositoryImpl(get()) }

    single<GetCompanySearchUseCase> { GetCompanySearchUseCaseImpl(get()) }
    single<GetCompanyReviewUseCase> { GetCompanyReviewUseCaseImpl(get()) }

    viewModel { SearchCompanyListViewModel(get()) }
    viewModel { SearchCompanyDetailViewModel(get()) }
}