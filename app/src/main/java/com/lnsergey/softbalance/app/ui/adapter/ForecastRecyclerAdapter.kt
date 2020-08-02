package com.lnsergey.softbalance.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lnsergey.softbalance.R
import com.lnsergey.softbalance.app.data.model.Daily
import com.lnsergey.softbalance.databinding.ItemRecyclerDayBinding

class ForecastRecyclerAdapter(
    private var dataList: List<Daily> = arrayListOf()
) : RecyclerView.Adapter<ForecastRecyclerAdapter.ForecastRecyclerHolder>(){

    fun setData(data: ArrayList<Daily>) {
        dataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastRecyclerHolder =
        ForecastRecyclerHolder(
            DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_recycler_day,
            parent,
            false
        ))

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ForecastRecyclerHolder, position: Int) {
        holder.binding.data = dataList[position]
    }

    class ForecastRecyclerHolder(val binding: ItemRecyclerDayBinding): RecyclerView.ViewHolder(binding.root)

}