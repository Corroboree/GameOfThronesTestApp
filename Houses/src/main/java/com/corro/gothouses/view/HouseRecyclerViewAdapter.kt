package com.corro.gothouses.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.corro.gothouses.R
import com.corro.gothouses.model.House
import kotlinx.android.synthetic.main.layout_simple_two_row_list_item.view.*

/**
 * Created by Jan on 23.01.2020
 */
class HouseRecyclerViewAdapter(var data: List<House> = listOf(), private val listener: View.OnClickListener) :
        RecyclerView.Adapter<HouseRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_simple_two_row_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.houseName.text = item.name
        holder.houseRegion.text = item.region
        holder.view.tag = item.url.substringAfterLast("/").toInt()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val houseName: TextView = view.tv_house_name
        val houseRegion: TextView = view.tv_house_region

        init {
            view.setOnClickListener(listener)
        }
    }
}