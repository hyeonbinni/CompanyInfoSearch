package com.hyeonbinni.companyinfosearch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.hyeonbinni.domain.entity.CellTypeReviewItem
import com.hyeonbinni.domain.usecase.GetCompanyReviewUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchCompanyDetailViewModel(
    private val getCompanyReviewUseCase: GetCompanyReviewUseCase
) : ViewModel() {

    private val _searchReviewListLiveData = MutableLiveData<PagingData<CellTypeReviewItem>>()
    val searchReviewListLiveData: LiveData<PagingData<CellTypeReviewItem>> = _searchReviewListLiveData

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getCompanyReview(companyId: Int) {
        getCompanyReviewUseCase.invoke(companyId)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { data ->
                    data?.run {
                        _searchReviewListLiveData.value = this
                    }
                },
                onError = {
                    it.printStackTrace()
                },
                onComplete = {

                }
            )
    }

}