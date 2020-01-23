package com.corro.gothouses.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.corro.gothouses.R
import com.corro.gothouses.viewmodel.HouseViewModel
import kotlinx.android.synthetic.main.fragment_house_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Jan on 23.01.2020
 */
class HousesFragment : Fragment() {

    private val viewModel by viewModel<HouseViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_house_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        viewModel.houseList.observe(this, Observer {
            with(list) {
                layoutManager = LinearLayoutManager(context)
                adapter = HouseRecyclerViewAdapter(it)
            }
        })
    }

    companion object {
        fun newInstance() = HousesFragment()
    }
}