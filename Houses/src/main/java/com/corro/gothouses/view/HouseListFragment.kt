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
class HouseListFragment : Fragment(), View.OnClickListener {

    private val viewModel by viewModel<HouseViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_house_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.adapter = HouseRecyclerViewAdapter(listener=this)
        initObserver()
//        viewModel.requestHouseList()
        toolbar.title = getString(R.string.toolbar_title)
    }

    private fun initObserver() {
        viewModel.loadingStatus.observe(this, Observer {
            when (it) {
                is HouseViewModel.Status.StartLoading -> {
                    progressbar.visibility = View.VISIBLE
                }
                is HouseViewModel.Status.FinishLoadingList -> {
                    progressbar.visibility = View.INVISIBLE
                    with(list) {
                        layoutManager = LinearLayoutManager(context)
                        with(adapter as HouseRecyclerViewAdapter){
                            data = it.list
                            notifyDataSetChanged()
                        }
                        addItemDecoration(MarginDividerItemDecoration(context, 24))
                    }
                }
                is HouseViewModel.Status.ErrorLoading -> {
                }
                else -> Unit
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.loadingStatus.removeObservers(this)
    }

    override fun onClick(view: View?) {
        if (view?.tag is Int) {
            (activity as MainActivity).replaceFragment(HouseDetailFragment.newInstance(view.tag as Int), true)
        }
    }

    companion object {
        fun newInstance() = HouseListFragment()
    }
}