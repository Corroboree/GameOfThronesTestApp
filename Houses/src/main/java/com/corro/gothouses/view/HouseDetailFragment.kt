package com.corro.gothouses.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.corro.gothouses.R
import com.corro.gothouses.model.House
import com.corro.gothouses.viewmodel.HouseViewModel
import kotlinx.android.synthetic.main.fragment_house_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Jan on 23.01.2020
 */
class HouseDetailFragment : Fragment() {

    private val viewModel by viewModel<HouseViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_house_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObserver()
        arguments?.getInt(EXTRA_ID).let {
            if (it!! > 0) {
                viewModel.loadHouseDetails(arguments!!.getInt(EXTRA_ID))
            }
        }
    }

    private fun initObserver() {
        viewModel.loadingStatus.observe(this, Observer {
            when (it) {
                is HouseViewModel.Status.StartLoading -> {
                }
                is HouseViewModel.Status.FinishLoadingDetails -> showDetails(it.house)
                is HouseViewModel.Status.ErrorLoading -> {
                }
                else -> Unit
            }
        })
    }

    private fun showDetails(house: House) {
        house.name.let {
            layout_house_name.visibility = View.VISIBLE
            tv_details_name.text = it
        }
        house.region.let {
            layout_house_region.visibility = View.VISIBLE
            tv_details_region.text = it
        }
        house.coatOfArms.let {
            layout_house_coat_of_arms.visibility = View.VISIBLE
            tv_details_coat_of_arms.text = it
        }
    }

    companion object {
        private const val EXTRA_ID = "extraId"
        fun newInstance(id: Int) = HouseDetailFragment().apply {
            arguments = bundleOf(EXTRA_ID to id)
        }
    }
}