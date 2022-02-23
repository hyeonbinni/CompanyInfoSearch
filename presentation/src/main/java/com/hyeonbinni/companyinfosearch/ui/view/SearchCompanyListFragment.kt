package com.hyeonbinni.companyinfosearch.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyeonbinni.companyinfosearch.CompanySearchItemListAdapter
import com.hyeonbinni.companyinfosearch.databinding.FragmentSearchCompanyListBinding
import com.hyeonbinni.companyinfosearch.ui.viewmodel.SearchCompanyListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCompanyListFragment : Fragment(){

    private val cellTypeCompanyItemListAdapter = CompanySearchItemListAdapter()

    private val viewModel: SearchCompanyListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchCompanyListBinding.inflate(inflater, container, false)

        with(binding) {
            with(rvCellTypeCompany) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.VERTICAL
                }

                adapter = cellTypeCompanyItemListAdapter
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeSearchCompanyResultEvent()

        viewModel.getCompanySearch("company_name")

        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeSearchCompanyResultEvent() {
        viewModel.searchCompanyListLiveData.observe(viewLifecycleOwner, Observer { data ->
            data?.run {
                cellTypeCompanyItemListAdapter.submitData(lifecycle, data)
            }
        })
    }
}