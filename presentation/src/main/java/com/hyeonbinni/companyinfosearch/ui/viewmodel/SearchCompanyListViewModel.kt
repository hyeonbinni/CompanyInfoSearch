package com.hyeonbinni.companyinfosearch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.hyeonbinni.domain.entity.CellTypeItem
import com.hyeonbinni.domain.usecase.GetCompanySearchUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchCompanyListViewModel(
    private val getCompanySearchUseCase: GetCompanySearchUseCase
) : ViewModel() {

    private val _searchCompanyListLiveData = MutableLiveData<PagingData<CellTypeItem.Item>>()
    val searchCompanyListLiveData: LiveData<PagingData<CellTypeItem.Item>> = _searchCompanyListLiveData

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getCompanySearch(query: String) {
        getCompanySearchUseCase.invoke(query)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { data ->
                    data?.run {
                        _searchCompanyListLiveData.value = this
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