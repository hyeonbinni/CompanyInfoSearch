package com.hyeonbinni.companyinfosearch.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hyeonbinni.companyinfosearch.CellTypeReviewItemListAdapter
import com.hyeonbinni.companyinfosearch.databinding.FragmentSearchCompanyDetailBinding
import com.hyeonbinni.companyinfosearch.ui.viewmodel.SearchCompanyDetailViewModel

import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCompanyDetailFragment : Fragment() {

    private val cellTypeReviewItemListAdapter = CellTypeReviewItemListAdapter()

    private val args: SearchCompanyDetailFragmentArgs by navArgs()

    private val viewModel: SearchCompanyDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchCompanyDetailBinding.inflate(inflater, container, false)

        with(binding) {
            with(args) {
                Glide.with(root)
                    .load(logoPath)
                    .into(ivLogo)

                tvCompanyName.text = companyName
                tvRateTotalAvg.text = rateTotalAvg.toString()
                tvIndustryName.text = industryName
            }

            with(rvCellTypeReview) {
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.VERTICAL
                }

                adapter = cellTypeReviewItemListAdapter
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeSearchCompanyDetailEvent()

        val companyId = args.companyId
        viewModel.getCompanyReview(companyId)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeSearchCompanyDetailEvent() {
        viewModel.searchReviewListLiveData.observe(viewLifecycleOwner, Observer { data ->
            data?.run {
                cellTypeReviewItemListAdapter.submitData(lifecycle, this)
            }
        })
    }
}